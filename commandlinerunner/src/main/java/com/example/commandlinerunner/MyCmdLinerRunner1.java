package com.example.commandlinerunner;

import com.example.commandlinerunner.bean.MyBean;
import com.example.commandlinerunner.bean.MyServers;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by WangYx on 2017/9/14.
 */
@Component
@Order(6)
@Log
public class MyCmdLinerRunner1 implements CommandLineRunner {

    @Autowired
    private MyBean myBean;
    @Autowired
    private MyServers myServers;

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCmdLinerRunner1:order = " + OrderUtils.getOrder(this.getClass())+" :args = "
                + Arrays.toString(args));
        System.out.println(myBean.getName()+"  "+myBean.getNumber());
        System.out.println(myServers.getUsername()+" and "
                + myServers.getRemoteAddress());
    }

    private String getStacks(){
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        sb.append("=============\n");

        for (int i = 0 ; i < elements.length ; i++){
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }
}
