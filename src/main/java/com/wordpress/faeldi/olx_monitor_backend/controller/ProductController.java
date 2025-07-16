package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.dto.FoundProductRequest;
import com.wordpress.faeldi.olx_monitor_backend.service.FoundProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final FoundProductService service;

    public ProductController(FoundProductService service) {
        this.service = service;
    }

    @PostMapping("/found")
    public ResponseEntity<Void> registerFound(@Valid @RequestBody FoundProductRequest request) {
        service.registerFoundProduct(request);
        return ResponseEntity.ok().build();
    }
}
