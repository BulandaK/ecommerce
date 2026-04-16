package com.example.ecommerce.dto;

import java.math.BigDecimal;

public record ProductFilter(
        String name,
        BigDecimal priceFrom,
        BigDecimal priceTo,
        Long producerId
) {}
