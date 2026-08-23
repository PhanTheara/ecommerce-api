package com.istad.theara.ecommerce_api.features.category;

import com.istad.theara.ecommerce_api.features.category.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.features.category.dto.updateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")

public interface CategoryMapper {
   CategoryEntity mapToCategoryEntity(CategoryRequest categoryRequest);
   CategoryResponse mapToCategoryResponse(CategoryEntity category) ;
   void toEntity(updateCategoryRequest updateCategoryRequest,@MappingTarget CategoryEntity categoryEntity);
}