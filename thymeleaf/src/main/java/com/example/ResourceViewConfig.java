package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by WangYx on 2017/9/12.
 */
@Configuration
@EnableWebMvc
public class ResourceViewConfig {

    @Bean
    public InternalResourceViewResolver resolver()throws Exception{
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setViewClass(JstlView.class);
        return resolver;

    }

//    @Bean
//    public TemplateResolver templateResolver()throws Exception{
//        TemplateResolver templateResolver = new TemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/templates");
//
//
//        return templateResolver;
//    }
}
