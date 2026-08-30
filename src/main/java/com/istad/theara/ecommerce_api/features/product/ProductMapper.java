package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.features.category.CategoryMapper;
import com.istad.theara.ecommerce_api.features.product.dto.CreateProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.PatchProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.ProductResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = { CategoryMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(PatchProductRequest dto, @MappingTarget ProductEntity entity);

    @Mapping(source = "categoryEntity", target = "category")
    ProductResponse toProductResponse(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryEntity", ignore = true)
    ProductEntity mapProduct(CreateProductRequest product);
}