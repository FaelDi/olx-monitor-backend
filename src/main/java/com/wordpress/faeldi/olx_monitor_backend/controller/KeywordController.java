package com.wordpress.faeldi.olx_monitor_backend.controller;

import com.wordpress.faeldi.olx_monitor_backend.model.Keyword;
import com.wordpress.faeldi.olx_monitor_backend.model.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.KeywordRepository;
import com.wordpress.faeldi.olx_monitor_backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;

    public KeywordController(UserRepository userRepository, KeywordRepository keywordRepository) {
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
    }

    @PostMapping
    public ResponseEntity<?> addKeyword(@AuthenticationPrincipal UserDetails userDetails,
                                        @Valid @RequestBody Keyword keyword) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        long count = keywordRepository.countByUserId(user.getId());
        if (count >= user.getRole().getKeywordLimit()) {
            return ResponseEntity.badRequest().body("Keyword limit reached");
        }
        keyword.setUser(user);
        keywordRepository.save(keyword);
        return ResponseEntity.ok(keyword);
    }
}
