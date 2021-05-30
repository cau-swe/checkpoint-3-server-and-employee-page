package com.example.pizza.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderDatabaseConnection orderDatabaseConnection;

    @GetMapping("/orders/{id}")
    public String findOrder(@PathVariable Long id) {
        Optional<Order> find = orderDatabaseConnection.findById(id);
        if (find.isPresent()) {
            return "order-detail";
        }
        return "not-found";
    }
}
