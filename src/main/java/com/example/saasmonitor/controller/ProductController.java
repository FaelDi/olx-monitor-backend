package com.example.saasmonitor.controller;

import com.example.saasmonitor.dto.FoundProductRequest;
import com.example.saasmonitor.service.FoundProductService;
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
