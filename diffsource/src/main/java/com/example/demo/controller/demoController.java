package com.example.demo.controller;

import com.example.demo.service.demo1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangYx on 2018/4/13.
 */
@RestController
public class demoController {

    @Autowired
    private demo1Service demo1Service;

    @RequestMapping("/demo")
    public String getDemo(){
        try {
            boolean b = demo1Service.getDemo1();
            if (b){
                return "true";
            }
        } catch (Exception e) {
            return "false";
        }
        return null;
    }
}
