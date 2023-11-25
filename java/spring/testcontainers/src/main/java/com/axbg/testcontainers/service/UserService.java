package com.axbg.testcontainers.service;

import com.axbg.testcontainers.entity.User;
import com.axbg.testcontainers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User storeUser(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return userRepository.save(user);
    }
}
