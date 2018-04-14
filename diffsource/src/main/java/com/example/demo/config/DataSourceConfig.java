package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by WangYx on 2018/4/13.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dev01DS")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource getDev01DataSource()throws Exception{
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    @Bean(name = "dev02DS")
    @ConfigurationProperties(prefix = "test.datasource")
    public DataSource getDev02DataSource()throws Exception{
        return DataSourceBuilder.create().build();
    }

}
