package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WangYx on 2018/4/13.
 */
@Data
public class Demo1Dto implements Serializable {

    private static final long serialVersionUID = -4150872227676584067L;

    private Integer id;
    private String name;
    private Integer age;

}
