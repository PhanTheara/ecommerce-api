package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.features.product.dto.CreateProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
            productService.CreateNew(createProductRequest);
     }
     @GetMapping()
     @ResponseStatus(HttpStatus.OK)
     public Page<ProductResponse> findAll(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return  productService.findAll(pageNumber, pageSize);
     }

}
