package com.istad.theara.ecommerce_api.features.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductRequest(
        String name,
        Integer qty,
        String description,
        BigDecimal unitPrice,
        String thumbnail,
        Long categoryId,
        Boolean isAvailable,
        List< Long> tagIds
) {}
