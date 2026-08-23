package com.istad.theara.ecommerce_api.features.media;

import com.istad.theara.ecommerce_api.features.category.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {
    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.client-path}")
    private String mediaClientPath;

    @Value("${media.base-uri}")
    private String mediaBaseUri;
    private final MediaRepository mediaRepository;
    private  final MediaMapper mediaMapper;



    @Override
    public MediaResponse addMedia(MultipartFile file) {
        String name = UUID.randomUUID().toString();
        int lastIndexDto =file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastIndexDto +1);
        Path path = Paths.get(mediaLocation + name + "." + extension);
        log.info("upload file "+ path);

        try {
            Files.copy(file.getInputStream(),path);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Media Upload Failed");
        }

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setName(name);
        mediaEntity.setExtension(extension);
        mediaEntity.setMediaType(file.getContentType());
        mediaEntity.setIsDart(false);
        mediaEntity.setSize((float) file.getSize());
        mediaRepository.save(mediaEntity);

        Float size = mediaEntity.getSize();


        return MediaResponse.builder()
                .id(mediaEntity.getId())
                .name(name)
                .extension(extension)
                .mediaType(file.getContentType())
                .uri(buildMediaUri(mediaEntity))
                .size(size)
                .build();

    }

    @Override
    public List<MediaResponse> addMedia(List<MultipartFile> file) {
        return file.stream().map(this::addMedia).toList();
    }

    @Override
    @Transactional
    public void deleteMedia(String name) {
        MediaEntity mediaEntity = mediaRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Media has not been found "));
         mediaRepository.delete(mediaEntity);
        Path path = Paths.get(buildMediaPath( mediaLocation,mediaEntity.getName(), mediaEntity.getExtension()));
        try {
            Files.delete(path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong"
            );
        }
    }
    private String buildMediaPath(
            String mediaLocation,
                                  String mediaName,
                                  String mediaExtension) {
        return mediaLocation+ mediaName + "." + mediaExtension;
    }

    private String buildMediaUri(MediaEntity mediaEntity) {
        return mediaBaseUri +
                mediaClientPath +
                "/" + mediaEntity.getName() +
                "." + mediaEntity.getExtension();
    }

    @Override
    public Page<MediaResponse> findAll(int pageNumber, int pageSize, Boolean isDraft) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByIdDesc);
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setIsDart(true);
        return mediaRepository.findAll(pageable).map(this::buildMediaResponse);
    }

    @Override
    public Page<MediaResponse> findAll(int pageNumber, int pageSize) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByIdDesc);

        return mediaRepository.findAll(pageable).map(this::buildMediaResponse);
    }


    private MediaResponse buildMediaResponse(MediaEntity media) {
        // 1MB = 1_000_000B
        return MediaResponse.builder()
                .id(media.getId())
                .name(media.getName())
                .extension(media.getExtension())
                .mediaType(media.getMediaType())
                .uri(buildMediaUri(media)) // http://localhost:1333/media/78689a24-551c-4575-9831-a4ec8e2bb0ef.png
                .build();
    }
    @Override
    public MediaResponse findByName(String name) {
        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media Name Empty");
        }
        MediaEntity mediaEntity = mediaRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media hs not been found "));
        return MediaResponse.builder()
                .id(mediaEntity.getId())
                .name(name)
                .extension(mediaEntity.getExtension())
                .mediaType(mediaEntity.getMediaType())
                .uri(buildMediaUri(mediaEntity))
                .build();
    }
}
