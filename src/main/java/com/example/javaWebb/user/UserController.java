package com.example.javaWebb.user;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> hello(@RequestBody CreateUserDTO dto) {
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
}
