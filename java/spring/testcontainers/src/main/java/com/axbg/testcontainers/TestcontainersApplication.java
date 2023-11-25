package com.axbg.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestcontainersApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestcontainersApplication.class, args);
    }
}
