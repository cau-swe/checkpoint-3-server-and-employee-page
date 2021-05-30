package com.example.pizza.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuDatabaseConnection menuDatabaseConnection;

    @GetMapping("/menus")
    ResponseEntity<List<MenuResponse>> findAll() {
        List<Menu> menus = menuDatabaseConnection.findAll();
        return ResponseEntity.ok(menus.stream()
                .map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice()))
                .collect(Collectors.toList()));
    }
}
