package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by WangYx on 2017/9/27.
 */
public class EurekaController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam String name){
        return "Hello EurekaClient " + name + " , I am from 节点: " + port;
    }
}
