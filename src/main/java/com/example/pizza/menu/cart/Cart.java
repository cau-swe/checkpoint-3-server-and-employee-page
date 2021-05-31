package com.example.pizza.menu.cart;

import com.example.pizza.account.User;
import com.example.pizza.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int amount;

    public Cart(Long id, Long userId, Long menuId, int amount) {
        this(id, new User(userId, null, null), new Menu(menuId, null, null, 0), amount);
    }
}
