package com.example.pizza.menu.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDatabaseConnection extends JpaRepository<Cart, Long> {
}
