package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Long producerId,
        String producerName,
        List<ProductAttributeResponse> attributes
) {}
