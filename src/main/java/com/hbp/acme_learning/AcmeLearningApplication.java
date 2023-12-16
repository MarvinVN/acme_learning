package com.hbp.acme_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hbp.acme_learning.repository")
public class AcmeLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeLearningApplication.class, args);
	}

}
