package spring.mybatis.dynamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.mybatis.dynamic.mapperb.MapperB;
import spring.mybatis.dynamic.service.ServiceA;

/**
 * Created by WangYx on 2018/4/14.
 */
@RestController
public class ControllerA {

    @Autowired
    ServiceA serviceA;
    @Autowired
    MapperB mapperB;


    @RequestMapping("/A")
    public String getA(){
        String result = serviceA.getResult();

        return result;
    }

    @RequestMapping("/B")
    public String getB(){
        Integer count = mapperB.calcTableCount();

        return count+"";
    }

}
