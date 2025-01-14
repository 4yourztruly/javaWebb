package com.example.javaWebb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JavaWebbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebbApplication.class, args);
	}

}
