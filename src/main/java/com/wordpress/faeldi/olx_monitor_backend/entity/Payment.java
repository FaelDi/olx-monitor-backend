package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCreditsPurchased() {
        return creditsPurchased;
    }

    public void setCreditsPurchased(Integer creditsPurchased) {
        this.creditsPurchased = creditsPurchased;
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
