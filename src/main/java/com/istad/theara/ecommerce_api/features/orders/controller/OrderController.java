package com.istad.theara.ecommerce_api.features.orders.controller;//package com.istad.theara.ecommerce_api.features.order.controller;

import com.istad.theara.ecommerce_api.features.orders.Dto.OrderResponse;
import com.istad.theara.ecommerce_api.features.orders.Dto.SaleOrderRequest;
import com.istad.theara.ecommerce_api.features.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody SaleOrderRequest saleOrderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saleOrders(saleOrderRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @Valid @RequestBody SaleOrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/me")
    public ResponseEntity<List<OrderResponse>> getMyOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}