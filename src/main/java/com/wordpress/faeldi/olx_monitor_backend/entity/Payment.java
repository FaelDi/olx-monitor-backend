package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private User user;

    private Double amount;

    private Integer creditsPurchased;

    private LocalDateTime subscriptionUntil;

    private String paymentMethod;
}
