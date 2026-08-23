package com.istad.theara.ecommerce_api.features.media;

import com.istad.theara.ecommerce_api.features.category.dto.MediaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("media")
public class MediaController {
    @Autowired
    private MediaService mediaService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        mediaService.deleteMedia(name);
    }

    @PostMapping("/upload")
    public MediaResponse upload(@RequestPart MultipartFile file) {
       return    mediaService.addMedia(file);
    }

    @PostMapping("/uploads")
    public List<MediaResponse> upload(@RequestPart List<MultipartFile> file) {
        return  mediaService.addMedia(file);
    }

    @PostMapping("/{name}")
    public MediaResponse updateCategory(@PathVariable String name) {
        return mediaService.findByName(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MediaResponse> findAll(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return  mediaService.findAll(pageNumber, pageSize);
    }
}
