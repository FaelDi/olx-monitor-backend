package com.wordpress.faeldi.olx_monitor_backend.repository;

import com.wordpress.faeldi.olx_monitor_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
