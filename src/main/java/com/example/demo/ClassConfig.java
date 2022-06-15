package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.demo.domain")
@EnableJpaRepositories(basePackages = "com.example.demo.repositories")
public class ClassConfig {

}
