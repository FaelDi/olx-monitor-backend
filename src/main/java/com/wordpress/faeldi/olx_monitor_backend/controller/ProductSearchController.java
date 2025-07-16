package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.dto.ProductSearchRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.ProductSearch;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import com.wordpress.faeldi.olx_monitor_backend.service.ProductSearchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/searches")
public class ProductSearchController {

    private final ProductSearchService service;
    private final UserRepository userRepository;

    public ProductSearchController(ProductSearchService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ProductSearch> create(@AuthenticationPrincipal UserDetails userDetails,
                                                @Valid @RequestBody ProductSearchRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        ProductSearch ps = service.create(user, request);
        return ResponseEntity.ok(ps);
    }

    @GetMapping
    public ResponseEntity<List<ProductSearch>> list(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(service.list(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails,
                                       @PathVariable UUID id) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        service.delete(id, user);
        return ResponseEntity.ok().build();
    }
}
