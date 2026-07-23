package com.istad.theara.ecommerce_api.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        String code,
        String slug,
        String name,
        String qty,
        BigDecimal unitPrice,
        String thumbnail,
        Boolean isAvailable,
        String  description,
        List<CategoryResponse> categoryList,
        List<TagResponse> tagList
) {}
