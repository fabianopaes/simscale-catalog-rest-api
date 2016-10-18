package com.simscale.catalog.rest;

import com.simscale.catalog.rest.web.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages ="com.simscale.catalog.rest.service")
@SpringBootApplication
@EnableJpaRepositories(basePackages="com.simscale.catalog.rest.domain")
public class AppInitializer {

    public static void main(String[] args) {
        Object[] configurations = new Object[] {
                AppInitializer.class,
                UserController.class
        };
        SpringApplication.run(configurations, args);
    }


}
