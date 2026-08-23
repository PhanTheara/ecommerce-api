package com.istad.theara.ecommerce_api.features.product;

import com.istad.theara.ecommerce_api.app.entity.OrderLinesEntity;
import com.istad.theara.ecommerce_api.features.category.CategoryEntity;
import com.istad.theara.ecommerce_api.features.tag.TagEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(name = "tbl_product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 250, nullable = false,unique = true)
    private String code;
    @Column(length = 250, nullable = false,unique = true)
    private String slug;
    @Column( nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false,length = 500)
    private String description;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private String thumbnail;
    @Column(nullable = false)
    private Boolean isAvailable;
    @Column(nullable = false)
    private Boolean isDeleted;
    @ManyToOne
    private CategoryEntity categoryEntity;
    @OneToMany
    private List<OrderLinesEntity> productLines;
    @ManyToMany
    @JoinTable(name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagEntity> tagEntities;
}
