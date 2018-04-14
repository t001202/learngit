/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nutch.hostdb;

import java.io.IOException;
import java.lang.invoke.MethodHandles;


import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.CrawlDb;
import org.apache.nutch.crawl.NutchWritable;
import org.apache.nutch.metadata.Nutch;
import org.apache.nutch.net.URLFilters;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.protocol.ProtocolStatus;
import org.apache.nutch.util.URLUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper ingesting HostDB and CrawlDB entries. Additionally it can also read
 * host score info from a plain text key/value file generated by the
 * Webgraph's NodeDumper tool.
 */
public class UpdateHostDbMapper
  implements Mapper<Text, Writable, Text, NutchWritable> {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  protected Text host = new Text();
  protected HostDatum hostDatum = null;
  protected CrawlDatum crawlDatum = null;
  protected String reprUrl = null;
  protected String buffer = null;
  protected String[] args = null;
  protected boolean filter = false;
  protected boolean normalize = false;
  protected boolean readingCrawlDb = false;
  protected URLFilters filters = null;
  protected URLNormalizers normalizers = null;

  public void close() {}

  /**
   * @param job
   */
  public void configure(JobConf job) {
    readingCrawlDb = job.getBoolean("hostdb.reading.crawldb", false);
    filter = job.getBoolean(UpdateHostDb.HOSTDB_URL_FILTERING, false);
    normalize = job.getBoolean(UpdateHostDb.HOSTDB_URL_NORMALIZING, false);

    if (filter)
      filters = new URLFilters(job);
    if (normalize)
      normalizers = new URLNormalizers(job, URLNormalizers.SCOPE_DEFAULT);
  }

  /**
   * Filters and or normalizes the input URL
   *
   * @param url
   * @return String
   */
  protected String filterNormalize(String url) {
    // We actually receive a hostname here so let's make a URL
    // TODO: we force shop.fcgroningen to be https, how do we know that here?
    // http://issues.openindex.io/browse/SPIDER-40
    url = "http://" + url + "/";

    try {
      if (normalize)
        url = normalizers.normalize(url, URLNormalizers.SCOPE_DEFAULT);
      if (filter)
        url = filters.filter(url);
      if (url == null)
        return null;
    } catch (Exception e) {
      return null;
    }

    // Turn back to host
    return URLUtil.getHost(url);
  }

  /**
    * Mapper ingesting records from the HostDB, CrawlDB and plaintext host
    * scores file. Statistics and scores are passed on.
    *
    * @param key
    * @param value
    * @param output
    * @param reporter
    */
  public void map(Text key, Writable value,
    OutputCollector<Text,NutchWritable> output, Reporter reporter)
    throws IOException {

    // Get the key!
    String keyStr = key.toString();

    // Check if we process records from the CrawlDB
    if (key instanceof Text && value instanceof CrawlDatum) {
      // Get the normalized and filtered host of this URL
      buffer = filterNormalize(URLUtil.getHost(keyStr));

      // Filtered out?
      if (buffer == null) {
        reporter.incrCounter("UpdateHostDb", "filtered_records", 1);
        LOG.info("UpdateHostDb: " + URLUtil.getHost(keyStr) + " crawldatum has been filtered");
        return;
      }

      // Set the host of this URL
      host.set(buffer);
      crawlDatum = (CrawlDatum)value;
      hostDatum = new HostDatum();

      /**
        * TODO: fix multi redirects: host_a => host_b/page => host_c/page/whatever
        * http://www.ferienwohnung-armbruster.de/
        * http://www.ferienwohnung-armbruster.de/website/
        * http://www.ferienwohnung-armbruster.de/website/willkommen.php
        *
        * We cannot reresolve redirects for host objects as CrawlDatum metadata is
        * not available. We also cannot reliably use the reducer in all cases
        * since redirects may be across hosts or even domains. The example
        * above has redirects that will end up in the same reducer. During that
        * phase, however, we do not know which URL redirects to the next URL.
        */
      // Do not resolve homepages when the root URL is unfetched
      if (crawlDatum.getStatus() != CrawlDatum.STATUS_DB_UNFETCHED) {
        // Get the protocol
        String protocol = URLUtil.getProtocol(keyStr);
        
        // Get the proposed homepage URL
        String homepage = protocol + "://" + buffer + "/";

        // Check if the current key is equals the host
        if (keyStr.equals(homepage)) {
          // Check if this is a redirect to the real home page
          if (crawlDatum.getStatus() == CrawlDatum.STATUS_DB_REDIR_PERM ||
            crawlDatum.getStatus() == CrawlDatum.STATUS_DB_REDIR_TEMP) {

            // Obtain the repr url for this redirect via protocolstatus from the metadata
            ProtocolStatus z = (ProtocolStatus)crawlDatum.getMetaData().
              get(Nutch.WRITABLE_PROTO_STATUS_KEY);

            // Get the protocol status' arguments
            args = z.getArgs();

            // ..and the possible redirect URL
            reprUrl = args[0];

            // Am i a redirect?
            if (reprUrl != null) {
              LOG.info("UpdateHostDb: homepage: " + keyStr + " redirects to: " + args[0]);
              output.collect(host, new NutchWritable(hostDatum));
              hostDatum.setHomepageUrl(reprUrl);
            } else {
              LOG.info("UpdateHostDb: homepage: " + keyStr + 
                " redirects to: " + args[0] + " but has been filtered out");
            }
          } else {
            hostDatum.setHomepageUrl(homepage);
            output.collect(host, new NutchWritable(hostDatum));
            LOG.info("UpdateHostDb: homepage: " + homepage);
          }
        }
      }

      // Always emit crawl datum
      output.collect(host, new NutchWritable(crawlDatum));
    }

    // Check if we got a record from the hostdb
    if (key instanceof Text && value instanceof HostDatum) {
      buffer = filterNormalize(keyStr);

      // Filtered out?
      if (buffer == null) {
        reporter.incrCounter("UpdateHostDb", "filtered_records", 1);
        LOG.info("UpdateHostDb: " + key.toString() + " hostdatum has been filtered");
        return;
      }

      // Get a HostDatum
      hostDatum = (HostDatum)value;
      key.set(buffer);

      // If we're also reading CrawlDb entries, reset db_* statistics because
      // we're aggregating them from CrawlDB anyway
      if (readingCrawlDb) {
        hostDatum.resetStatistics();
      }

      output.collect(key, new NutchWritable(hostDatum));
    }

    // Check if we got a record with host scores
    if (key instanceof Text && value instanceof Text) {
      buffer = filterNormalize(keyStr);

      // Filtered out?
      if (buffer == null) {
        reporter.incrCounter("UpdateHostDb", "filtered_records", 1);
        LOG.info("UpdateHostDb: " + key.toString() + " score has been filtered");
        return;
      }

      key.set(buffer);

      output.collect(key,
        new NutchWritable(new FloatWritable(Float.parseFloat(value.toString()))));
    }
  }
}