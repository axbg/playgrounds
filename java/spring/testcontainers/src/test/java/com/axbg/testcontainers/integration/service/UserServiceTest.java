package com.axbg.testcontainers.integration.service;

import com.axbg.testcontainers.entity.User;
import com.axbg.testcontainers.integration.config.TestDatabaseConfig;
import com.axbg.testcontainers.repository.UserRepository;
import com.axbg.testcontainers.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest extends TestDatabaseConfig {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByIdTest() {
        User initialUser = new User();
        initialUser.setName("Alex");
        initialUser.setAge(29);
        initialUser = userRepository.save(initialUser);

        User result = userService.findById(initialUser.getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Alex", result.getName());
    }

    @Test
    void storeUserTest() {
        User initialUser = userService.storeUser("Alex", 29);

        Optional<User> storedUser = userRepository.findById(initialUser.getId());
        Assertions.assertTrue(storedUser.isPresent());
        Assertions.assertEquals(initialUser.getId(), storedUser.get().getId());
        Assertions.assertEquals(initialUser.getName(), storedUser.get().getName());
    }
}
