package com.example.pizza.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    private Long id;
    private String name;
    private String description;
    private int price;
}
