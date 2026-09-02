package com.istad.theara.ecommerce_api.features.orders.service.impl;
import com.istad.theara.ecommerce_api.app.Enums.OrderStatus;
import com.istad.theara.ecommerce_api.features.orders.Dto.SaleOrderRequest;
import com.istad.theara.ecommerce_api.features.orders.Dto.OrderResponse;
import com.istad.theara.ecommerce_api.features.orders.Entity.OrderEntity;
import com.istad.theara.ecommerce_api.features.orders.Entity.OrderLinesEntity;
import com.istad.theara.ecommerce_api.features.orders.mapper.OrderMapper;
import com.istad.theara.ecommerce_api.features.orders.repository.OrderRepository;
import com.istad.theara.ecommerce_api.features.orders.service.OrderService;
import com.istad.theara.ecommerce_api.features.product.ProductEntity;
import com.istad.theara.ecommerce_api.features.product.ProductRepository;
// Adjust import based on your package structure
import com.istad.theara.ecommerce_api.features.util.AuthUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;


    @Override
    @Transactional
    public OrderResponse saleOrders(SaleOrderRequest saleOrderRequest) {
        String userId = AuthUtils.extractUserId();

        OrderEntity order = new OrderEntity();
        order.setAddress(saleOrderRequest.address());
        order.setCustomerId(userId);
        order.setDiscount(saleOrderRequest.discount() != null ? saleOrderRequest.discount() : BigDecimal.ZERO);
        order.setIsDeleted(false);
        order.setStatus(OrderStatus.PENDING);
        List<OrderLinesEntity> orderLines = calculateAndBuildOrderLines(saleOrderRequest, order);
        for (OrderLinesEntity line : orderLines) {
            line.setOrderEntity(order);
        }
        order.setOrderLinesEntity(orderLines);
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderLinesEntity line : orderLines) {
            subtotal = subtotal.add(line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQty())));
        }
        BigDecimal totalPrice = subtotal.subtract(order.getDiscount());
        order.setTotalPrice(totalPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : totalPrice);
        OrderEntity savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(Long id, SaleOrderRequest orderRequest) {
        String userId = AuthUtils.extractUserId();
        OrderEntity order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        order.setAddress(orderRequest.address());
        order.setCustomerId(userId);
        order.setDiscount(orderRequest.discount() != null ? orderRequest.discount() : BigDecimal.ZERO);

        order.getOrderLinesEntity().clear();
        List<OrderLinesEntity> updatedLines = calculateAndBuildOrderLines(orderRequest, order);
        order.getOrderLinesEntity().addAll(updatedLines);

        BigDecimal subtotal = updatedLines.stream()
                .map(line -> line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrice = subtotal.subtract(order.getDiscount());
        order.setTotalPrice(totalPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : totalPrice);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.toResponse(orderRepository.findAllByIsDeletedFalse());
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerId() {
        String customerId = AuthUtils.extractUserId();
        return orderMapper.toResponse(orderRepository.findAllByCustomerIdAndIsDeletedFalse(customerId));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        OrderEntity order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        OrderEntity order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    private List<OrderLinesEntity> calculateAndBuildOrderLines(SaleOrderRequest request, OrderEntity order) {
        List<OrderLinesEntity> lines = new ArrayList<>();

        request.orderItem().forEach(lineReq -> {
            ProductEntity product = productRepository.findById(lineReq.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + lineReq.productId()));

            OrderLinesEntity line = new OrderLinesEntity();
            line.setQty(lineReq.qty());
            line.setUnitPrice(lineReq.unitPrice());
            line.setProductEntity(product);
            line.setOrderEntity(order);
            lines.add(line);
        });

        return lines;
    }
}