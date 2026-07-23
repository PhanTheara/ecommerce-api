package com.istad.theara.ecommerce_api.mapper;

import com.istad.theara.ecommerce_api.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.dto.updateCategoryRequest;
import com.istad.theara.ecommerce_api.entity.CategoryEntity;
import com.istad.theara.ecommerce_api.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
   CategoryEntity mapToCategoryEntity(CategoryRequest categoryRequest);
   CategoryResponse mapToCategoryResponse(CategoryEntity category) ;
  void toEntity(updateCategoryRequest updateCategoryRequest,@MappingTarget CategoryEntity categoryEntity);

}