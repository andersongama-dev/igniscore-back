package com.igniscore.api.controller;

import com.igniscore.api.dto.AutDTO;
import com.igniscore.api.dto.RegisterDTO;
import com.igniscore.api.dto.LoginResponseDTO;
import com.igniscore.api.model.User;
import com.igniscore.api.model.UserRole;
import com.igniscore.api.repository.UserRepository;
import com.igniscore.api.service.JwtService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository repository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid @NonNull AutDTO data) {
        var usernamepassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);

        var principal = auth.getPrincipal();
        if (!(principal instanceof User user)) {
            throw new RuntimeException("User authentication failed");
        }

        var token = jwtService.generateJwt(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User();
        newUser.setName(data.name());
        newUser.setEmail(data.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
