package com.example.pizza.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuDatabaseConnection menuDatabaseConnection;

    @GetMapping("/menus")
    public ResponseEntity<List<MenuResponse>> findAll() {
        List<Menu> menus = menuDatabaseConnection.findAll();
        return ResponseEntity.ok(menus.stream()
                .map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<MenuResponse> findPizza(@PathVariable Long id) {
        Optional<Menu> findMenu = menuDatabaseConnection.findById(id);
        if (findMenu.isPresent()) {
            Menu menu = findMenu.get();
            return ResponseEntity.ok(new MenuResponse(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice()));
        }
        return ResponseEntity.badRequest().build();
    }
}
