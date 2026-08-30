package com.istad.theara.ecommerce_api.features.orders.controller;//package com.istad.theara.ecommerce_api.features.order.controller;
//
//import com.istad.theara.ecommerce_api.features.order.Dto.OrderRequest;
//import com.istad.theara.ecommerce_api.features.order.Dto.OrderResponse;
//import com.istad.theara.ecommerce_api.features.order.service.OrderService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/orders")
//@RequiredArgsConstructor
//public class OrderController {
//
//    private final OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
//        return ResponseEntity.ok(orderService.updateOrder(id, orderRequest));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<OrderResponse>> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<List<OrderResponse>> getMyOrders(Authentication authentication) {
//        // Retrieve logged-in user's ID from security context
//        Long loggedInCustomerId = Long.parseLong(authentication.getName());
//        return ResponseEntity.ok(orderService.getOrdersByCustomerId(loggedInCustomerId));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
//        return ResponseEntity.ok(orderService.getOrderById(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.noContent().build();
//    }
//}