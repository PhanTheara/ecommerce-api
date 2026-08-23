package com.istad.theara.ecommerce_api.features.category.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record updateCategoryRequest(
        @Size(max = 100)
        String name,
        @Size(max = 500)
        String description,
        @Size(max = 250)
        String icon,
        @Positive
        Long parentCategoryId
) {
}
