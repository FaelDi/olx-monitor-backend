package com.wordpress.faeldi.olx_monitor_backend.dto;

import java.time.LocalDateTime;

public class PaymentRequest {
    private Double amount;
    private Integer credits;
    private LocalDateTime subscriptionUntil;
    private String paymentMethod;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public LocalDateTime getSubscriptionUntil() {
        return subscriptionUntil;
    }

    public void setSubscriptionUntil(LocalDateTime subscriptionUntil) {
        this.subscriptionUntil = subscriptionUntil;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
