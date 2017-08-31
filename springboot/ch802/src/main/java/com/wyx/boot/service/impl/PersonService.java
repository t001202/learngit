package com.wyx.boot.service.impl;

import com.wyx.boot.dao.PersonRepository;
import com.wyx.boot.domain.Person;
import com.wyx.boot.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by WangYx on 2017/8/31.
 */
@Service
public class PersonService implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    @Override
    @CachePut(value = "people",key = "#person.id")
    public Person save(Person person) {
        Person p1 = repository.save(person);
        logger.info("为id,key 为"+p1.getId()+"数据做了缓存");
        return p1;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        logger.info("删除了id,key为: " + id + " 的数据缓存");
        repository.delete(id);
    }

    @Override
    @Cacheable(value = "people",key = "#person.id")
    public Person findOne(Person person) {
        Person p3 = repository.getOne(person.getId());
        logger.info("为id,key为: " + p3.getId() + " 数据做了缓存");
        return p3;
    }
}
