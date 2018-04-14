package spring.mybatis.dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mybatis.dynamic.mappera.MapperA;
import spring.mybatis.dynamic.result.Result;

/**
 * Created by WangYx on 2018/4/14.
 */
@Service
public class ServiceA {

    @Autowired
    MapperA mapperA;

    public String getResult(){
        Integer count = mapperA.calcTableCount();
        if (count != null){
            return "true";
        }

        return "false";
    }
}
