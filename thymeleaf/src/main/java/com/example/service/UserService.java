package com.example.service;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangYx on 2017/9/17.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper mapper;


    public List<User> selectAllUser(){
        List<User> users = mapper.selectAll();
        return users;
    }
}
