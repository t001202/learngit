package com.wyx.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ch802Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch802Application.class, args);
	}
}
