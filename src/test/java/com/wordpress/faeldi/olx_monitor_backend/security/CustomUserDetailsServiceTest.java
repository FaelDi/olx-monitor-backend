package com.wordpress.faeldi.olx_monitor_backend.security;

import com.wordpress.faeldi.olx_monitor_backend.entity.PlanType;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new CustomUserDetailsService(userRepository);
    }

    @Test
    void userFoundReturnsDetails() {
        User user = new User();
        user.setEmail("a");
        user.setPassword("p");
        user.setPlanType(PlanType.MONTHLY);
        when(userRepository.findByEmail("a")).thenReturn(Optional.of(user));
        UserDetails details = service.loadUserByUsername("a");
        assertEquals("a", details.getUsername());
        assertEquals("p", details.getPassword());
        assertTrue(details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MONTHLY")));
    }

    @Test
    void userNotFoundThrows() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("x"));
    }
}
