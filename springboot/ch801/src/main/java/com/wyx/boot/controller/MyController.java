package com.wyx.boot.controller;

import com.wyx.boot.domain.Person;
import com.wyx.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangYx on 2017/8/31.
 */
@RestController
public class MyController {
    @Autowired
    PersonService service;

    @RequestMapping("/rollback")
    public Person rollback(Person person){
        return service.savePersonWithRollback(person);
    }

    @RequestMapping("/notrollback")
    public Person notrollback(Person person){
        return service.savePersonWithoutRollback(person);
    }
}
