package com.istad.theara.ecommerce_api.features.tag;


import com.istad.theara.ecommerce_api.features.tag.dto.TagRequest;
import com.istad.theara.ecommerce_api.features.tag.dto.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagEntity fromTagRequest(TagRequest tagRequest);

    void fromTagRequest(TagRequest tagRequest, @MappingTarget TagEntity tag);

    TagResponse toTagResponse(TagEntity tag);

}
