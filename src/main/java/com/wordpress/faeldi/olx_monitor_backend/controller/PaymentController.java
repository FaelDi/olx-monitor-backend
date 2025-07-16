package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.dto.PaymentRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.Payment;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import com.wordpress.faeldi.olx_monitor_backend.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;
    private final UserRepository userRepository;

    public PaymentController(PaymentService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping("/simulate")
    public ResponseEntity<Payment> simulate(@AuthenticationPrincipal UserDetails userDetails,
                                            @Valid @RequestBody PaymentRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Payment p = service.simulatePayment(user, request);
        return ResponseEntity.ok(p);
    }
}
