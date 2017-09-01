package com.wyx.boot.dao;

import com.wyx.boot.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by WangYx on 2017/9/1.
 */
@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

    Person findByName(String name);

    @Query("{'age':?0}")
    List<Person> withQueryFindByAge(Integer age);
}
