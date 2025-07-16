package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.dto.AuthResponse;
import com.wordpress.faeldi.olx_monitor_backend.dto.LoginRequest;
import com.wordpress.faeldi.olx_monitor_backend.dto.RegisterRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import com.wordpress.faeldi.olx_monitor_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(user);
    }
}
