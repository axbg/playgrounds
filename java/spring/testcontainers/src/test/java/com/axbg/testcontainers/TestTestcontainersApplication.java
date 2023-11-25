package com.axbg.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestcontainersApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestcontainersApplication::main).with(TestTestcontainersApplication.class).run(args);
    }

}
