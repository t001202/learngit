package spring.mybatis.dynamic.mappera;

import spring.mybatis.dynamic.dto.StudentDto;

import java.util.List;

/**
 * Created by WangYx on 2018/4/14.
 */
public interface MapperA {

    Integer calcTableCount();


    List<StudentDto> queryAllStudent();
}
