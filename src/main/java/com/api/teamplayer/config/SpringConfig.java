package com.api.teamplayer.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(value = {"com.api.teamplayer.config"})
@EnableJpaRepositories(basePackages = {
        "com.api.teamplayer.repository"})
@EntityScan(basePackages = {
        "com.api.teamplayer.entity"})
@EnableTransactionManagement
public class SpringConfig {

}
