package com.example.pizza.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDatabaseConnection extends JpaRepository<Order, Long> {
}
