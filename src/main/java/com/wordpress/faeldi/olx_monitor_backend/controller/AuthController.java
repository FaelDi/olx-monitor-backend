package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.dto.AuthResponse;
import com.wordpress.faeldi.olx_monitor_backend.dto.LoginRequest;
import com.wordpress.faeldi.olx_monitor_backend.dto.RegisterRequest;
import com.wordpress.faeldi.olx_monitor_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
