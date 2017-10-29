package com.wxy.hsk.timer;

import lombok.extern.java.Log;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.SchedulingConfiguration;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by WangYx on 2017/10/26.
 */
@Component
@Log
public class scheduledConfiguration extends SchedulingConfiguration{
    public static void main(String[] args) {
        Date current_date = new Date();
        Date date = DateUtils.addDays(current_date, 3);
        System.out.println(date);
    }


}
