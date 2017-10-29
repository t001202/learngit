package com.wxy.hsk.timer;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by WangYx on 2017/10/26.
 */
@Component
@Log
public class testTimer {

    @Scheduled(cron="0 0/1 8-23 * * ?")
    public void test(){
        Date date = new Date();
        System.out.println("进行定时器 定时输出。。。"+date);
    }
}
