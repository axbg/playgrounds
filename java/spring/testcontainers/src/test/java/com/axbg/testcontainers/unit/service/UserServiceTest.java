package com.axbg.testcontainers.unit.service;

import com.axbg.testcontainers.entity.User;
import com.axbg.testcontainers.repository.UserRepository;
import com.axbg.testcontainers.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void findByIdTest() {
        User mockUser = new User();
        mockUser.setName("Alex");
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(mockUser));

        User result = userService.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Alex", result.getName());
    }

    @Test
    void storeUserTest() {
        User mockUser = new User();
        mockUser.setName("Alex");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(mockUser);

        User result = userService.storeUser("Alex", 25);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Alex", result.getName());
    }
}
