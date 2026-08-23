package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.features.category.CategoryEntity;
import com.istad.theara.ecommerce_api.features.category.CategoryRepository;
import com.istad.theara.ecommerce_api.features.product.dto.CreateProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.PatchProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.ProductResponse;
import com.istad.theara.ecommerce_api.features.tag.TagEntity;
import com.istad.theara.ecommerce_api.features.tag.TagRepository;
import com.istad.theara.ecommerce_api.features.util.GenerateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse updateProduct(Long id, PatchProductRequest request) {
        ProductEntity validProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product has not been found"));

        // Validate category ID if client patch
        if (request.categoryId() != null) {
            CategoryEntity validCategory = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category has not been found"));
            validProduct.setCategoryEntity(validCategory);
        }

        // Validate tag IDs if client patch
        if (request.tags() != null) {
//            List<TagEntity> validTags = request.tags().stream().map(tagId -> tagRepository.findById(tagId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag ID = " + tagId + "has not been found"))).collect(Collectors.toList());
//            validProduct.setTagEntity(validTags);
            // List.of(1,2)
            // List.of(3)
        }

        productMapper.toEntity(request, validProduct);
        productRepository.save(validProduct);

        return productMapper.toProductResponse(validProduct);
    }

    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByIdDesc);
        return productRepository.findAll(pageable).map(productMapper::toProductResponse);
    }

    @Override
    public void CreateNew(CreateProductRequest productRequest) {

        CategoryEntity validaCategory = categoryRepository.findById(productRequest.categoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        List<TagEntity> listTage = productRequest.tagIds().stream().map(tagId -> tagRepository.findById(tagId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag Not Found"))).toList();

        ProductEntity toProduct = productMapper.mapProduct(productRequest);
        toProduct.setCode(GenerateUtils.getProductCode());
        toProduct.setSlug(GenerateUtils.toSlug(toProduct.getName()));
        toProduct.setCategoryEntity(validaCategory);
        //   toProduct.setTagEntity(listTage);
        toProduct.setIsDeleted(false);
        productRepository.save(toProduct);

    }

}
