package com.courseapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.courseapp.repositories" })
@EntityScan(basePackages = { "com.courseapp.domain" })
@ComponentScan(basePackages = { "com.courseapp.exception", "com.courseapp.rest", "com.courseapp.rest.validation",
		"com.courseapp.service" })
public class PracticeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
		        //.allowedOrigins("http://localhost:3000")
				.allowedOrigins("*")
		        .allowedMethods("GET", "POST","PUT", "DELETE")
		        .allowedHeaders("*");
			}
		};
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {	
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:ValidationMessages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}
	
}
