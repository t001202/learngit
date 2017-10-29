package com.example.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WangYx on 2017/9/12.
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = 2694720646679925332L;

    private Integer id = 1;
    private String name ;
    private String message = "心情不美丽";
    private String msg;
    private String age;
    private String address;
    private String phone;
    private String job;
}
