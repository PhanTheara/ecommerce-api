package com.istad.theara.ecommerce_api.features.category.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        Long id,
        String name,
        String extension,
        String mediaType,
        Float size,
        String uri

) {
}
