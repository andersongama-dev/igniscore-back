package com.igniscore.api.service;

import com.igniscore.api.model.User;
import com.igniscore.api.repository.UserRepository;
import com.igniscore.api.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getId(), user.getEmail());
    }
}
