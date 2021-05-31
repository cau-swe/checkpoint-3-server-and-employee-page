package com.example.pizza.order;

import com.example.pizza.menu.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String findOrder(@PathVariable Long id, Model model) {
        Optional<Order> find = orderDatabaseConnection.findById(id);
        if (find.isPresent()) {
            Order order = find.get();
            model.addAttribute("order", order);
            model.addAttribute("carts", order.getCarts());
            model.addAttribute("user", order.getCarts().get(0).getUser());

            int totalPrice = order.getCarts()
                    .stream()
                    .map(Cart::getPrice)
                    .mapToInt(Integer::intValue)
                    .sum();

            model.addAttribute("totalPrice", totalPrice);
            return "order-detail";
        }
        return "not-found";
    }

    @PostMapping("/orders/{id}/state")
    public String changeOrderState(@PathVariable Long id, @RequestBody String orderState, Model model) {
        Optional<Order> find = orderDatabaseConnection.findById(id);
        if (find.isPresent()) {
            Order order = find.get();
            order.changeState(orderState);
            Order saved = orderDatabaseConnection.save(order);
            model.addAttribute("order", saved);
            model.addAttribute("carts", order.getCarts());
            model.addAttribute("user", order.getCarts().get(0).getUser());
            return "order-detail";
        }
        return "not-found";
    }
}
