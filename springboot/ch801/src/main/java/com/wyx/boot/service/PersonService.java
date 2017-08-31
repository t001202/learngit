package com.wyx.boot.service;

import com.wyx.boot.domain.Person;
import org.slf4j.LoggerFactory;

/**
 * Created by WangYx on 2017/8/31.
 */
public interface PersonService {
    public Person savePersonWithoutRollback(Person person);
    public Person savePersonWithRollback(Person person);
}
