package com.istad.theara.ecommerce_api.features.orders.repository;

import com.istad.theara.ecommerce_api.features.orders.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Optional<OrderEntity> findByIdAndIsDeletedFalse(Long id);
    List<OrderEntity> findAllByIsDeletedFalse();
    List<OrderEntity> findAllByCustomerIdAndIsDeletedFalse(String customerId);
}
