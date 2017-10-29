package com.example.solr;

import com.example.solr.utils.SystemConfig;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class DemoApplication {

	public static void main(String[] args) {
		log.info("SystemConfig : "+ SystemConfig.get("spring.mail.port"));
		SpringApplication.run(DemoApplication.class, args);
	}
}
