package com.wyx.boot.service.impl;

import com.wyx.boot.dao.PersonRepository;
import com.wyx.boot.domain.Person;
import com.wyx.boot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WangYx on 2017/8/31.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository repository;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithRollback(Person person) {
        Person p1 = repository.save(person);
        if (person.getName().equals("ww")){
            logger.info(person.getName()+ "已经存在,数据将回滚");
            throw new IllegalArgumentException(person.getName()+ "已经存在,数据将回滚");
        }
        return p1;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollback(Person person) {
        Person p2 = repository.save(person);
        if (person.getName().equals("ww")){
            logger.info(person.getName()+ "虽已经存在,数据将不会回滚");
            throw new IllegalArgumentException(person.getName() + "虽已经存在,数据将不会回滚");
        }
        return p2;
    }
}
