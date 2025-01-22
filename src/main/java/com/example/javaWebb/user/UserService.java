package com.example.javaWebb.user;

import com.example.javaWebb.security.JWTService;
import com.example.javaWebb.security.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoder;
    private final JWTService jwtService;

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

    public String loginUser(String username, String password) {
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

        return jwtService.generateToken(user.getId());
    }

    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> verifyAuthentication(String token) {
        try{
            UUID userId = jwtService.verifyToken(token);
            return findById(userId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
