package com.example.commandlinerunner.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Created by WangYx on 2017/9/14.
 */
@Component
@Data
public class MyBean {

    @Value("${name}")
    private String name;
    @Value("${my.secret")
    private String secret;
    @Value("${my.number}")
    private Integer number;
    @Value("${my.bignumber}")
    private Long bignumber;
    @Value("${my.number.less.than.ten}")
    private Integer thanTen;
    @Value("${my.number.in.range}")
    private Integer inRange;

}
