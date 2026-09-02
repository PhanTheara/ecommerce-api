package com.istad.theara.ecommerce_api.features.orders.Entity;

import com.istad.theara.ecommerce_api.app.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tbl_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    String customerId;
    @Column(length = 255,name = "STORE_ID")
    String storeId;
    @Column(nullable = false)
    BigDecimal discount = BigDecimal.ZERO;
    @Column(nullable = false)
    Boolean isDeleted = false;
    @Column(nullable = false)
    BigDecimal totalPrice = BigDecimal.ZERO;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    OrderStatus status;
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderLinesEntity> orderLinesEntity = new ArrayList<>();
}