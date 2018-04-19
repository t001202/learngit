package com.huali.ai.aiban;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "mybatis.mapper")
public class AibanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AibanApplication.class, args);
	}
}
