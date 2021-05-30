package com.example.pizza.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDatabaseConnection extends JpaRepository<Menu, Long> {
}
