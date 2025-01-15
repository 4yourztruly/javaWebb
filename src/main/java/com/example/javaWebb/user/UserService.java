package com.example.javaWebb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String username, String password) {
        if(username.isEmpty()) {
            throw new IllegalArgumentException("Username can not be empty.");
        }

        if(password.isEmpty()) {
            throw new IllegalArgumentException("Password may not be empty.");
        }

        User user = new User(username,password);
        return userRepository.save(user);
    }

}
