package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record ProducerRequest(
        @NotBlank String name,
        String description
) {}