package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductAttributeRequest(
        @NotBlank String attributeKey,
        String attributeValue
) {}