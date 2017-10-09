package com.example.commandlinerunner.bean;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by WanYx on 2017/9/14.
 */
@Component
@ConfigurationProperties(prefix = "connection")
@Data
public class MyServers {

    private String username;
    @NotNull
    private String remoteAddress;
}
