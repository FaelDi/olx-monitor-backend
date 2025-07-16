package com.wordpress.faeldi.olx_monitor_backend.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ProductSearch {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private User user;

    private String query;

    private boolean active = true;

    private int frequencyMinutes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getFrequencyMinutes() {
        return frequencyMinutes;
    }

    public void setFrequencyMinutes(int frequencyMinutes) {
        this.frequencyMinutes = frequencyMinutes;
    }
}
