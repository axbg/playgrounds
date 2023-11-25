package com.axbg.testcontainers.repository;

import com.axbg.testcontainers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u order by u.age desc")
    List<User> findUsersOrderedByAgeDesc();
}
