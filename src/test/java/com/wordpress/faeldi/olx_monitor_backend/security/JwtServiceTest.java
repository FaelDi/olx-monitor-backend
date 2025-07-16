package com.wordpress.faeldi.olx_monitor_backend.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setup() throws Exception {
        jwtService = new JwtService();
        Field f = JwtService.class.getDeclaredField("secret");
        f.setAccessible(true);
        // secret must be base64 encoded, here 32 bytes key -> 256 bits
        f.set(jwtService, java.util.Base64.getEncoder().encodeToString("12345678901234567890123456789012".getBytes()));
    }

    @Test
    void generateAndExtract() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("k", "v");
        String token = jwtService.generateToken(claims, "subj");
        assertNotNull(token);
        Claims extracted = jwtService.extractAllClaims(token);
        assertEquals("subj", extracted.getSubject());
        assertEquals("v", extracted.get("k", String.class));
        assertEquals("subj", jwtService.extractUsername(token));
    }
}
