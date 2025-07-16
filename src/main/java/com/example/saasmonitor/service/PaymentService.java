package com.example.saasmonitor.service;

import com.example.saasmonitor.dto.PaymentRequest;
import com.example.saasmonitor.entity.Payment;
import com.example.saasmonitor.entity.User;
import com.example.saasmonitor.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment simulatePayment(User user, PaymentRequest request) {
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(request.getAmount());
        payment.setCreditsPurchased(request.getCredits());
        payment.setSubscriptionUntil(request.getSubscriptionUntil());
        payment.setPaymentMethod(request.getPaymentMethod());
        user.setCredits(user.getCredits() + (request.getCredits() != null ? request.getCredits() : 0));
        if (request.getSubscriptionUntil() != null) {
            user.setSubscriptionExpiresAt(request.getSubscriptionUntil());
        }
        return repository.save(payment);
    }
}
