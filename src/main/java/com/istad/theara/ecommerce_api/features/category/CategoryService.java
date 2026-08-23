package com.istad.theara.ecommerce_api.features.category;

import com.istad.theara.ecommerce_api.features.category.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.features.category.dto.SearchCategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.updateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponse createNew(CategoryRequest categoryRequest);
    /* select category all*/
    Page<CategoryResponse> findAll(Pageable pageable);

    CategoryResponse updateCategory(Long id,updateCategoryRequest updateCategoryRequest);
   void deleteCategory(Long id);
   CategoryResponse findById(Long id);

   CategoryResponse searchCategory(String name);
}
