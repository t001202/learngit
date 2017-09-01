package com.wyx.boot.controller;

import com.wyx.boot.dao.PersonRepository;
import com.wyx.boot.domain.Location;
import com.wyx.boot.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by WangYx on 2017/9/1.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(){
        Person person = new Person("wyx", 11);
        LinkedHashSet<Location> locations = new LinkedHashSet<>();
        Location loc1 = new Location("上海", "2009");
        Location loc2 = new Location("北京", "2010");
        Location loc3 = new Location("昆明", "2011");
        Location loc4 = new Location("成都", "2012");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        person.setLocations(locations);
        return personRepository.save(person);
    }

    @RequestMapping("/q1")
    public Person query1(String name){
        return  personRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age){
        return personRepository.withQueryFindByAge(age);
    }

}
