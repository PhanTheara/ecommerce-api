package com.istad.theara.ecommerce_api.features.orders.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record SaleOrderRequest(
        @NotBlank(message = "Address is required")
        String address,
        @NotNull(message = "Customer ID is required")
        Long customerId,
        BigDecimal discount,
        @NotEmpty(message = "Order lines cannot be empty")
        @Valid
        List<OrderLineRequest> orderItem
) {}