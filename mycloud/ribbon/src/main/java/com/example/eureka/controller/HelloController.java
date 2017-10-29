package com.example.eureka.controller;

import com.example.eureka.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangYx on 2017/9/27.
 */
@RestController
public class HelloController {
    @Autowired
    HelloService service;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return service.hiService(name);
    }
}
