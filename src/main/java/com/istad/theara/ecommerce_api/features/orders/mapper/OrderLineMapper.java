package com.istad.theara.ecommerce_api.features.orders.mapper;

import com.istad.theara.ecommerce_api.features.orders.Dto.OrderLineResponse;
import com.istad.theara.ecommerce_api.features.orders.Entity.OrderLinesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public interface OrderLineMapper {

    @Mapping(
            source = "productEntity.id",
            target = "productId",
            defaultExpression = "java(orderLinesEntity.getProductEntity() != null ? orderLinesEntity.getProductEntity().getId() : null)"
    )
    OrderLineResponse toResponse(OrderLinesEntity orderLinesEntity);

    List<OrderLineResponse> toResponseList(List<OrderLinesEntity> orderLinesEntities);
}