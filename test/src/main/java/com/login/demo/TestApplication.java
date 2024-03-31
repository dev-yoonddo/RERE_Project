package com.login.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication(scanBasePackages = "com.login.demo")
public class TestApplication {
//	@Bean
//	public InternalResourceViewResolver setupViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);
		System.out.println("시작 !!!!!!!!");
	}

}
