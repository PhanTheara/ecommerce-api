package com.istad.theara.ecommerce_api.features.orders.service;

import com.istad.theara.ecommerce_api.features.orders.Dto.SaleOrderRequest;
import com.istad.theara.ecommerce_api.features.orders.Dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saleOrders(SaleOrderRequest orderRequest);
    OrderResponse updateOrder(Long id, SaleOrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByCustomerId();
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
}