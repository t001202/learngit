package com.wyx.boot.controller;

import com.wyx.boot.domain.Person;
import com.wyx.boot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangYx on 2017/8/31.
 */
@RestController
public class CacheController {
    @Autowired
    DemoService service;

    @RequestMapping("/put")
    public Person put(Person person){
        return service.save(person);
    }

    @RequestMapping("/able")
    public Person cacheable(Person person){
        Person p2 = service.findOne(person);
        return p2;
    }

    @RequestMapping("/evit")
    public String evit(Long id){
        try {
            service.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "删除成功";
    }
}
