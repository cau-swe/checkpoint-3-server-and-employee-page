package com.example.pizza.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginVerifier {
    private final PasswordEncoder passwordEncoder;

    public boolean verify(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
