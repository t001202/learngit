package com.example.solr.bean;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by WangYx on 2017/9/25.
 */
@Component
public class MyBean {

    private SolrServer solrServer;
    private ElasticsearchTemplate template;

    @Autowired
    public MyBean(SolrServer solrServer , ElasticsearchTemplate template) {
        this.solrServer = solrServer;
        this.template = template;
    }

}
