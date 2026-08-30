package com.istad.theara.ecommerce_api.features.orders.mapper;

import com.istad.theara.ecommerce_api.features.orders.Dto.OrderResponse;
import com.istad.theara.ecommerce_api.features.orders.Entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "orderLinesEntity", target = "orderLines")
        // Remove the custom @Mapping for status if entity.getStatus() is already an OrderStatus enum
    OrderResponse toResponse(OrderEntity entity);

    List<OrderResponse> toResponse(List<OrderEntity> orderEntities);
}