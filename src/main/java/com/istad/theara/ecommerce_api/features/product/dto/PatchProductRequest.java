package com.istad.theara.ecommerce_api.features.product.dto;

import com.istad.theara.ecommerce_api.features.tag.dto.TagResponse;

import java.math.BigDecimal;
import java.util.List;

public record PatchProductRequest(
        String name,
        String qty,
        BigDecimal unitPrice,
        String thumbnail,
        Boolean isAvailable,
        String  description,
        Long categoryId,
        List<TagResponse> tags
) {}
