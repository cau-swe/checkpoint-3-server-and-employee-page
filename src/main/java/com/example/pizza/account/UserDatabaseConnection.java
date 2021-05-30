package com.example.pizza.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDatabaseConnection extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
