package com.istad.theara.ecommerce_api.features.order;

import com.istad.theara.ecommerce_api.app.entity.OrderLinesEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "tbl_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String address;
    @Column(length = 255, nullable = false)
    private Long customerId;
    @Column(length = 255, nullable = false)
    private BigDecimal discount;
    @Column(length = 255, nullable = false)
    private Boolean isDeleted;
    @Column(length = 255, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(length = 255, nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderLinesEntity> orderLinesEntity;
}
