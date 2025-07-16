package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class FoundProduct {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private ProductSearch search;

    private String title;

    private String link;

    private Double price;

    private LocalDateTime postedAt;

    private boolean sentToUser = false;
}
