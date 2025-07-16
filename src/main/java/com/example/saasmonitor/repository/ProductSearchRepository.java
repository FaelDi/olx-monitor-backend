package com.example.saasmonitor.repository;

import com.example.saasmonitor.entity.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductSearchRepository extends JpaRepository<ProductSearch, UUID> {
    List<ProductSearch> findByUserId(UUID userId);
}
