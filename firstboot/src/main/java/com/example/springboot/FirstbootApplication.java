package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstbootApplication {

	public static void main(String[] args) {
		System.out.println("查看springboot 原生态 自动添加的jar dependency");
		SpringApplication.run(FirstbootApplication.class, args);
	}
}
