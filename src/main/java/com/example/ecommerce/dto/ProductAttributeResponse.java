package com.example.ecommerce.dto;

public record ProductAttributeResponse(
        Long id,
        String attributeKey,
        String attributeValue
) {}