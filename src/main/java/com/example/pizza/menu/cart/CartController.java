package com.example.pizza.menu.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartController {
    private final CartDatabaseConnection cartDatabaseConnection;

    @PostMapping("/carts")
    public ResponseEntity<CartResponse> addPizzaToCart(@RequestBody AddPizzaToCartRequest request) {
        Cart saved = cartDatabaseConnection.save(new Cart(null, request.getUserId(), request.getMenuId(), request.getAmount()));
        return ResponseEntity.ok(new CartResponse(saved.getId(), saved.getUser().getId(), saved.getMenu().getId(), saved.getAmount()));
    }
}
