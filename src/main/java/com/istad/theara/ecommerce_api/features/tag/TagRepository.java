package com.istad.theara.ecommerce_api.features.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    boolean existsByName(String name);

    Optional<TagEntity> findByName(String name);

}
