package com.istad.theara.ecommerce_api.features.orders.service;

import com.istad.theara.ecommerce_api.features.orders.Dto.OrderRequest;
import com.istad.theara.ecommerce_api.features.orders.Dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Long id, OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrdersByCustomerId(Long customerId);
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
}