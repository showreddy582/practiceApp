package com.courseapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.courseapp.repositories"})
@EntityScan(basePackages={"com.courseapp.domain"})
@ComponentScan(basePackages={"com.courseapp.exception","com.courseapp.rest","com.courseapp.rest.validation","com.courseapp.service"})
public class PracticeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeAppApplication.class, args);
	}
}
