package spring.mybatis.dynamic.mapperb;

import spring.mybatis.dynamic.dto.StudentDto;

/**
 * Created by WangYx on 2018/4/14.
 */
public interface MapperB {
    Integer calcTableCount();

    int updateStuTable(StudentDto student);
}
