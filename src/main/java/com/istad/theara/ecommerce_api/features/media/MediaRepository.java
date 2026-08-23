package com.istad.theara.ecommerce_api.features.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository  extends JpaRepository<MediaEntity, Long> {
    Optional<MediaEntity> findByName(String name);
}
