package com.example.javaWebb.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO dto) {
        try{
            var user = userService.createUser(dto.username, dto.password);
            return ResponseEntity.ok(user.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class CreateUserDTO {
        private String username;
        private String password;
    }

    @Data
    public static class LoginUserDTO {
        private String username;
        private String password;
    }
}
