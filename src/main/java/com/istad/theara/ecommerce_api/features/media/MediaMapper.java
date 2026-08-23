package com.istad.theara.ecommerce_api.features.media;

import com.istad.theara.ecommerce_api.features.category.dto.MediaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface MediaMapper {
    MediaResponse toMediaResponse(MediaEntity MediaEntity);

}
