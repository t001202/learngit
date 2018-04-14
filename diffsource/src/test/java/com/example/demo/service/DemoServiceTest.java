package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by WangYx on 2018/4/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoServiceTest {

    @Autowired
    demo1Service demo1;
    @Autowired
    demo2Service demo2;

    @Test
    public void testDemo1(){
        boolean flag = demo1.getDemo1();
        System.out.println(flag);
    }


    @Test
    public void testDemo2(){
        boolean flag = demo2.getDemo1();
        System.out.println(flag);
    }

}
