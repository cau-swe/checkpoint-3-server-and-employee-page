package com.example.pizza;

import com.example.pizza.account.User;
import com.example.pizza.account.UserDatabaseConnection;
import com.example.pizza.menu.Menu;
import com.example.pizza.menu.MenuDatabaseConnection;
import com.example.pizza.menu.cart.Cart;
import com.example.pizza.menu.cart.CartDatabaseConnection;
import com.example.pizza.order.Order;
import com.example.pizza.order.OrderDatabaseConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class PizzaApplication implements ApplicationRunner {
    private final UserDatabaseConnection userDatabaseConnection;
    private final MenuDatabaseConnection menuDatabaseConnection;
    private final CartDatabaseConnection cartDatabaseConnection;
    private final OrderDatabaseConnection orderDatabaseConnection;

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        userDatabaseConnection.save(new User(null, "종업원", "1234"));
        User orderer = userDatabaseConnection.save(new User(null, "홍길동", "1234"));
        Menu menu = menuDatabaseConnection.save(new Menu(null, "페퍼로니 피자", "", 18000));
        Menu menu2 = menuDatabaseConnection.save(new Menu(null, "포테이토 피자", "", 16000));
        Cart cart = cartDatabaseConnection.save(new Cart(null, orderer, menu, 2));
        Cart cart2 = cartDatabaseConnection.save(new Cart(null, orderer, menu2, 1));
        orderDatabaseConnection.save(new Order(null, orderer.getId(), Arrays.asList(cart, cart2), "대기 중"));
    }
}
