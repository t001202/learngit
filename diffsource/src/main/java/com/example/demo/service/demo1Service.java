package com.example.demo.service;

import com.example.demo.dto.Demo1Dto;
import com.example.demo.mapper1.demo1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangYx on 2018/4/13.
 */
@Service
public class demo1Service {

    @Autowired
    private demo1Mapper mapper;

    public boolean getDemo1(){
        Demo1Dto demo = mapper.queryDemo1();
        if (demo != null){
            return true;
        }
        return false;
    }

}
