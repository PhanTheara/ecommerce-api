package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.features.category.CategoryMapper;
import com.istad.theara.ecommerce_api.features.product.dto.CreateProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.PatchProductRequest;
import com.istad.theara.ecommerce_api.features.product.dto.ProductResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryMapper.class
        }
)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(PatchProductRequest dto, @MappingTarget ProductEntity entity);

    // Tell MapStruct explicitly which source fields map to which target fields
    @Mapping(source = "categoryEntity", target = "category")
    ProductResponse toProductResponse(ProductEntity productEntity);

    ProductEntity mapProduct(CreateProductRequest product);
}