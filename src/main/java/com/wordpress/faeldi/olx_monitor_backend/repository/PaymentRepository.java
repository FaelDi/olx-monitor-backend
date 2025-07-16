package com.wordpress.faeldi.olx_monitor_backend.repository;

import com.wordpress.faeldi.olx_monitor_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
