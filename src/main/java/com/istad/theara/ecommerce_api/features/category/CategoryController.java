package com.istad.theara.ecommerce_api.features.category;

import com.istad.theara.ecommerce_api.features.category.dto.CategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.CategoryResponse;
import com.istad.theara.ecommerce_api.features.category.dto.SearchCategoryRequest;
import com.istad.theara.ecommerce_api.features.category.dto.updateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping    
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.createNew(categoryRequest);
    }

    @RequestMapping()
    public Page<CategoryResponse> findAll(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "25") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return categoryService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory( @PathVariable Long id, @RequestBody updateCategoryRequest updateCategoryRequest) {
        return categoryService.updateCategory(id,updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public CategoryResponse searchCategoryByName(@PathVariable @Valid @RequestPart(required = false ) String name) {
        return categoryService.searchCategory(name);
    }
}
