package com.axbg.testcontainers.integration.repository;

import com.axbg.testcontainers.entity.User;
import com.axbg.testcontainers.integration.config.TestDatabaseConfig;
import com.axbg.testcontainers.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest extends TestDatabaseConfig {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUsersOrderedByAgeTest() {
        User user = new User();
        user.setName("Alex");
        user.setAge(25);
        userRepository.save(user);

        User user2 = new User();
        user2.setName("John");
        user2.setAge(41);
        userRepository.save(user2);

        List<User> result = userRepository.findUsersOrderedByAgeDesc();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get(0));
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertNotNull(result.get(1));
        Assertions.assertEquals("Alex", result.get(1).getName());
    }
}
