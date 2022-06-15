package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({ClassConfig.class})
public class GourmetAppSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GourmetAppSpringApplication.class, args);
		
	}

}
