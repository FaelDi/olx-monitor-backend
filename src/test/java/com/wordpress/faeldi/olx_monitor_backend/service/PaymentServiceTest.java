package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.PaymentRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.Payment;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new PaymentService(paymentRepository);
    }

    @Test
    void simulatePaymentCreatesAndSaves() {
        User user = new User();
        user.setCredits(1);
        PaymentRequest request = new PaymentRequest();
        request.setAmount(10.0);
        request.setCredits(5);
        request.setSubscriptionUntil(LocalDateTime.now().plusDays(1));
        request.setPaymentMethod("card");

        Payment saved = new Payment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(saved);

        Payment result = service.simulatePayment(user, request);
        assertSame(saved, result);
        ArgumentCaptor<Payment> captor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(captor.capture());
        Payment p = captor.getValue();
        assertEquals(user, p.getUser());
        assertEquals(10.0, p.getAmount());
        assertEquals(5, p.getCreditsPurchased());
        assertEquals("card", p.getPaymentMethod());
        assertEquals(request.getSubscriptionUntil(), p.getSubscriptionUntil());
        assertEquals(6, user.getCredits());
        assertEquals(request.getSubscriptionUntil(), user.getSubscriptionExpiresAt());
    }
}
