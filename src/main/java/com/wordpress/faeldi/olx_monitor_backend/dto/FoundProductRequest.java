package com.wordpress.faeldi.olx_monitor_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FoundProductRequest {
    private UUID searchId;
    private String title;
    private String link;
    private Double price;
    private LocalDateTime postedAt;
}
