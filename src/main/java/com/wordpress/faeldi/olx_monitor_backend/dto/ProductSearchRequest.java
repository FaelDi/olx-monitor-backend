package com.wordpress.faeldi.olx_monitor_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {
    @NotBlank
    private String query;
    @Min(5)
    private int frequencyMinutes;
}
