package com.istad.theara.ecommerce_api.features.product.dto;

import com.istad.theara.ecommerce_api.features.category.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.features.tag.dto.TagResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record ProductResponse(
        String name,
        String qty,
        BigDecimal unitPrice,
        String thumbnail,
        Boolean isAvailable,
        String  description,
        CategoryResponse category,  // Target field 1
        List<TagResponse> tags
) {}
