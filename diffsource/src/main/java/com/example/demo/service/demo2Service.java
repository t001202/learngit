package com.example.demo.service;

import com.example.demo.dto.Demo2Dto;
import com.example.demo.mapper2.demo2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangYx on 2018/4/13.
 */
@Service
public class demo2Service {

    @Autowired
    demo2Mapper mapper;

    public boolean getDemo1(){
        Demo2Dto demo = mapper.queryDemo2();
        if (demo != null){
            return true;
        }
        return false;
    }

}
