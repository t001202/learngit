package com.wxy.hsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimerApplication {

	public static void main(String[] args) {
		String str = new String();
		SpringApplication.run(TimerApplication.class, args);
	}
}
