package spring.mybatis.dynamic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WangYx on 2018/4/14.
 */
@Data
public class StudentDto implements Serializable{
    private static final long serialVersionUID = -6360325989480564984L;

    private Integer id;
    private String name;
    private Integer age;
}
