package com.wordpress.faeldi.olx_monitor_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentRequest {
    private Double amount;
    private Integer credits;
    private LocalDateTime subscriptionUntil;
    private String paymentMethod;
}
