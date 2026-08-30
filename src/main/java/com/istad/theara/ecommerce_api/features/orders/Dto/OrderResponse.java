package com.istad.theara.ecommerce_api.features.orders.Dto;

import com.istad.theara.ecommerce_api.app.Enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String address,
        Long customerId,
        BigDecimal discount,
        BigDecimal totalPrice,
        Boolean isDeleted,
        LocalDateTime createdAt,
        OrderStatus status,
        List<OrderLineResponse> orderLines
) {}