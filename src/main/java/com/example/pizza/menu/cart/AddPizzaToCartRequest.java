package com.example.pizza.menu.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddPizzaToCartRequest {
    private Long userId;
    private Long menuId;
    private int amount;
}
