package com.example.javaWebb.user;

import com.example.javaWebb.security.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoder;

    public User createUser(String username, String password) {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username can not be empty.");
        }

        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password may not be empty.");
        }

        if(userRepository.findUserByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already chosen, please pick another one.");
        }

        password = passwordEncoder.passwordEncoder().encode(password);

        User user = new User(username,password);
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is empty.");
        }

        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is empty.");
        }

        if(userRepository.findUserByUsername(username).isEmpty()) {
            throw new IllegalArgumentException("Account doesn't exist.");
        }

        User user = userRepository.findUserByUsername(username).get();

        if(!passwordEncoder.passwordEncoder().matches(password,user.getPassword())) {
            throw new IllegalArgumentException("Wrong credentials.");
        }

        return user;

    }

}
