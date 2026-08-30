package com.istad.theara.ecommerce_api.features.orders.Dto;

import java.math.BigDecimal;

public record OrderLineResponse(
        Long id,
        Integer qty,
        BigDecimal unitPrice,
        Long productId
) {}