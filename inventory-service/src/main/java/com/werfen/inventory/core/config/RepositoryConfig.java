package com.werfen.inventory.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.werfen.inventory.features"})
public class RepositoryConfig {

}