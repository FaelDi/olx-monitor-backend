package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductSearch getSearch() {
        return search;
    }

    public void setSearch(ProductSearch search) {
        this.search = search;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public boolean isSentToUser() {
        return sentToUser;
    }

    public void setSentToUser(boolean sentToUser) {
        this.sentToUser = sentToUser;
    }
}
