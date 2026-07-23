package com.istad.theara.ecommerce_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "tbl_tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,updatable = false)
    private String name;
    @Column(nullable = false,updatable = false)
    private Boolean isDeleted;
    @ManyToMany(mappedBy = "tagEntity")
    private List<ProductEntity> productEntity;
}
