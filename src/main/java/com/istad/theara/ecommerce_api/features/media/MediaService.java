package com.istad.theara.ecommerce_api.features.media;

import com.istad.theara.ecommerce_api.features.category.dto.MediaResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    Page<MediaResponse> findAll(int pageNumber, int pageSize,Boolean isDraft);

    Page<MediaResponse> findAll(int pageNumber, int pageSize);
    MediaResponse findByName(String name);
    MediaResponse addMedia(MultipartFile file);
    List<MediaResponse> addMedia(List<MultipartFile> file);
    void deleteMedia(String name);

}
