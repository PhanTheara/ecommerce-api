package com.istad.theara.ecommerce_api.features.tag;


import com.istad.theara.ecommerce_api.features.tag.dto.TagRequest;
import com.istad.theara.ecommerce_api.features.tag.dto.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    TagResponse createNew(TagRequest tagRequest);

    TagResponse updateById(Long id, TagRequest tagRequest);

    TagResponse findById(Long id);

    Page<TagResponse> findAll(Pageable pageable);

    void deleteById(Long id);

}
