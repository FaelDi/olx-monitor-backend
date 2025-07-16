package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.LoginRequest;
import com.wordpress.faeldi.olx_monitor_backend.dto.RegisterRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import com.wordpress.faeldi.olx_monitor_backend.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    private AuthService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new AuthService(userRepository, passwordEncoder, authenticationManager, jwtService);
    }

    @Test
    void registerSuccess() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("a@a.com");
        req.setPassword("pw");

        when(userRepository.existsByEmail("a@a.com")).thenReturn(false);
        when(passwordEncoder.encode("pw")).thenReturn("enc");

        service.register(req);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User saved = captor.getValue();
        assertEquals("a@a.com", saved.getEmail());
        assertEquals("enc", saved.getPassword());
    }

    @Test
    void registerDuplicate() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("a@a.com");
        when(userRepository.existsByEmail("a@a.com")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> service.register(req));
    }

    @Test
    void authenticateSuccess() {
        LoginRequest req = new LoginRequest();
        req.setEmail("a@a.com");
        req.setPassword("pw");
        User u = new User();
        u.setEmail("a@a.com");
        when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.of(u));
        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtService.generateToken(anyMap(), eq("a@a.com"))).thenReturn("tok");

        String tok = service.authenticate(req);
        assertEquals("tok", tok);
    }
}
