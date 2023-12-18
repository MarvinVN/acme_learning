package com.hbp.acme_learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.hbp.acme_learning.repository")
public class AcmeLearningConfig {
    
}
