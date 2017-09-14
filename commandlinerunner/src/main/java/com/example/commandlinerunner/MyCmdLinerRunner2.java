package com.example.commandlinerunner;

import com.example.commandlinerunner.bean.MyServers;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by WangYx on 2017/9/14.
 */
@Component
@Order(5)
@Log
@EnableConfigurationProperties(MyServers.class)
public class MyCmdLinerRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("MyCmdLinerRunner2:order = " + OrderUtils.getOrder(this.getClass())+" :args = "
                + Arrays.toString(args));
        System.out.println("");
    }


}
