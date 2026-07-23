package com.istad.theara.ecommerce_api.service;

import com.istad.theara.ecommerce_api.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.dto.updateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponse createNew(CategoryRequest categoryRequest);
    /* select category all*/
    Page<CategoryResponse> findAll(Pageable pageable);

    CategoryResponse updateCategory(Long id,updateCategoryRequest updateCategoryRequest);
   void deleteCategory(Long id);
   CategoryResponse findById(Long id);
}
