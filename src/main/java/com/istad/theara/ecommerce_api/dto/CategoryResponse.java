package com.istad.theara.ecommerce_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record CategoryResponse(
 Long id,
 String name,
 String description,
 String icon,
 @JsonInclude(JsonInclude.Include.NON_NULL)
 CategoryResponse parentCategory
) {}
