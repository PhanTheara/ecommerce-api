package com.istad.theara.ecommerce_api.features.category;

import com.istad.theara.ecommerce_api.features.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "tbl_category")
public class CategoryEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String icon;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> products;


}
