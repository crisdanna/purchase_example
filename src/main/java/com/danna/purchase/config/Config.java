package com.danna.purchase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Cristiane Danna
 */

@Configuration
@EnableJpaRepositories(basePackages = {"com.danna.purchase.repository"})
public class Config {

}
