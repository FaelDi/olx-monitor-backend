package com.example.saasmonitor.repository;

import com.example.saasmonitor.entity.FoundProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoundProductRepository extends JpaRepository<FoundProduct, UUID> {
    Optional<FoundProduct> findByLink(String link);
}
