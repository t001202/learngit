package spring.mybatis.dynamic.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.mybatis.dynamic.TestSupport;
import spring.mybatis.dynamic.dto.StudentDto;
import spring.mybatis.dynamic.mappera.MapperA;
import spring.mybatis.dynamic.mapperb.MapperB;

import java.util.List;

/**
 * Created by WangYx on 2018/4/14.
 */
public class MapperATest extends TestSupport {

    @Autowired
    MapperA mapperA;
    @Autowired
    MapperB mapperB;

    @Test
    public void testMapperA(){
        int count = mapperA.calcTableCount();
        System.out.println(count);
    }



    @Test
    public void testAllStud(){
        List<StudentDto> list = mapperA.queryAllStudent();
        for (StudentDto student : list) {
            mapperB.updateStuTable(student);
            System.out.println(student);
        }
    }
}
