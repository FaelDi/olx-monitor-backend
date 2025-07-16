package com.wordpress.faeldi.olx_monitor_backend.repository;

import com.wordpress.faeldi.olx_monitor_backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
