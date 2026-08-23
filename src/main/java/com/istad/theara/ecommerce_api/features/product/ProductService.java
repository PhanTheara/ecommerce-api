package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.features.product.dto.CreateProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.PatchProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse updateProduct(Long id, PatchProductRequest request);
    Page<ProductResponse> findAll(int pageNumber, int pageSize);
    void CreateNew(CreateProductRequest productRequest);
}
