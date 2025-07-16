package com.wordpress.faeldi.olx_monitor_backend.repository;

import com.wordpress.faeldi.olx_monitor_backend.entity.FoundProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoundProductRepository extends JpaRepository<FoundProduct, UUID> {
    Optional<FoundProduct> findByLink(String link);
}
