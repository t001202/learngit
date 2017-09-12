package test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;

/**
 * Created by WangYx on 2017/9/12.
 */
public class MessageController {

    @Value("https://spring.io/blog.atom")
    Resource resource;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller(){
//        PollerMetadata pollerMetadata = new PollerMetadata();
        return null;
    }

    @Bean
    public FeedEntryMessageSource messageSource()throws Exception{
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(),"news");
        return messageSource;
    }
}
