package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WangYx on 2018/4/13.
 */
@Data
public class Demo2Dto implements Serializable {
    private static final long serialVersionUID = -6640547071484119510L;


    private Integer id;
    private String name;
    private Integer age;
}
