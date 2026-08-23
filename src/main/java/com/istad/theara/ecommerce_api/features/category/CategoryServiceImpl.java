package com.istad.theara.ecommerce_api.features.category;
import com.istad.theara.ecommerce_api.features.category.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.features.category.dto.updateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createNew(CategoryRequest categoryRequest) {

        // TODO:
        // 1. Validate all information form DTO
        // Validate category name (unique)
        Optional<CategoryEntity> category = categoryRepository.findByName(categoryRequest.name());

        if (category.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category already exists");
        }

        CategoryEntity newCategory = categoryMapper.mapToCategoryEntity(categoryRequest);
        newCategory.setIsDeleted(false);

        // Validate parent category id
        if (categoryRequest.parentCategoryId() != null) {
            CategoryEntity parentCategory = categoryRepository.findById(categoryRequest.parentCategoryId()).orElseThrow();
            newCategory.setParentCategory(parentCategory);
        }
        newCategory = categoryRepository.save(newCategory);
        return categoryMapper.mapToCategoryResponse(newCategory);
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<CategoryEntity> categoryEntityList =  categoryRepository.findAll(pageable);
      return categoryEntityList.map(categoryMapper::mapToCategoryResponse);
    }

    @Override
    public CategoryResponse updateCategory(Long id,updateCategoryRequest updateCategoryRequest) {
        CategoryEntity categoryEntity  = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        if (categoryRepository.existsByName(updateCategoryRequest.name())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category already exists");
        }
        categoryMapper.toEntity(updateCategoryRequest,categoryEntity);
        CategoryEntity category = categoryRepository.save(categoryEntity);
        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity categoryEntity  = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::mapToCategoryResponse).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public CategoryResponse searchCategory(String name) {
       return categoryRepository.findByName(name).map(categoryMapper::mapToCategoryResponse).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }
}
