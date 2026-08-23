package com.istad.theara.ecommerce_api.app.entity;

import com.istad.theara.ecommerce_api.features.order.OrderEntity;
import com.istad.theara.ecommerce_api.features.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "tbl_order_line")
public class OrderLinesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private  Integer qty;
    @Column(length = 250, nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne
    private ProductEntity productEntity;
    @ManyToOne
    private OrderEntity orderEntity;
}
