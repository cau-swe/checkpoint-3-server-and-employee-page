package com.example.pizza.account;

import com.example.pizza.db.UserDatabaseConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final UserDatabaseConnection userDatabaseConnection;
    private final LoginVerifier loginVerifier;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userDatabaseConnection.findByName(loginRequest.getName())
                .orElseThrow(RuntimeException::new);
        if (loginVerifier.verify(loginRequest.getPassword(), user)) {
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getName(), user.getPassword()));
        }
        return ResponseEntity.badRequest().build();
    }
}
