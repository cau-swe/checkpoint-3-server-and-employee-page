package com.example.pizza.order;

import com.example.pizza.menu.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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
            return renderOrderDetail(model, order);
        }
        return "not-found";
    }

    @PostMapping("/orders/{id}/state")
    public String changeOrderState(@PathVariable Long id, HttpServletRequest request, Model model) {
        Optional<Order> find = orderDatabaseConnection.findById(id);
        if (find.isPresent()) {
            Order order = find.get();
            order.changeState(request.getParameter("state"));
            Order saved = orderDatabaseConnection.save(order);
            return renderOrderDetail(model, saved);
        }
        return "not-found";
    }

    @NotNull
    private String renderOrderDetail(Model model, Order order) {
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
}
