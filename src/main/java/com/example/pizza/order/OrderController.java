package com.example.pizza.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/orders/{id}/state")
    public String changeOrderState(@PathVariable Long id, @RequestBody String orderState) {
        Optional<Order> find = orderDatabaseConnection.findById(id);
        if (find.isPresent()) {
            Order order = find.get();
            order.changeState(orderState);
            orderDatabaseConnection.save(order);
            return "order-detail";
        }
        return "not-found";
    }
}
