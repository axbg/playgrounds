package com.axbg.testcontainers;

import com.axbg.testcontainers.integration.config.TestDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestcontainersApplicationTests extends TestDatabaseConfig {
    @Test
    void contextLoads() {
    }
}
