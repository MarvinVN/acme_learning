package com.hbp.acme_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hbp.acme_learning.repository")
public class AcmeLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeLearningApplication.class, args);
	}

}
