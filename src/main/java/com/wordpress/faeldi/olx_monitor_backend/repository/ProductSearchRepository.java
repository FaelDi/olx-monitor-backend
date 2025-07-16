package com.wordpress.faeldi.olx_monitor_backend.repository;

import com.wordpress.faeldi.olx_monitor_backend.entity.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductSearchRepository extends JpaRepository<ProductSearch, UUID> {
    List<ProductSearch> findByUserId(UUID userId);
}
