package com.example.pizza.account;

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
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        User user = userDatabaseConnection.findByName(userRequest.getName())
                .orElseThrow(RuntimeException::new);
        if (loginVerifier.verify(userRequest.getPassword(), user)) {
            return ResponseEntity.ok(new UserResponse(user.getId(), user.getName(), user.getPassword()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest userRequest) {
        String encodePassword = loginVerifier.encode(userRequest.getPassword());
        User user = userDatabaseConnection.save(new User(null, userRequest.getName(), encodePassword));
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getName(), user.getPassword()));
    }
}
