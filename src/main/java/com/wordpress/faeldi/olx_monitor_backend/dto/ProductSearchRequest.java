package com.wordpress.faeldi.olx_monitor_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductSearchRequest {
    @NotBlank
    private String query;
    @Min(5)
    private int frequencyMinutes;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getFrequencyMinutes() {
        return frequencyMinutes;
    }

    public void setFrequencyMinutes(int frequencyMinutes) {
        this.frequencyMinutes = frequencyMinutes;
    }
}
