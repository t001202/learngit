package com.wyx.boot.service;

import com.wyx.boot.domain.Person;

/**
 * Created by WangYx on 2017/8/31.
 */
public interface DemoService {

    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);
}
