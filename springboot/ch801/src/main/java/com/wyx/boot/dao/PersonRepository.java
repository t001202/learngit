package com.wyx.boot.dao;

import com.wyx.boot.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by WangYx on 2017/8/31.
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
}
