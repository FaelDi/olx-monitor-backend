package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Long telegramChatId;

    @Enumerated(EnumType.STRING)
    private PlanType planType = PlanType.CREDITS;

    private Integer credits = 0;

    private LocalDateTime subscriptionExpiresAt;
}
