package com.example.springgallery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springgallery.service.ImageService;
import com.example.springgallery.viewmodel.UploadImageVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of image service.
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UploadImageVM> uploadImage(MultipartFile... files) {

        Map<String, Object> params = ObjectUtils.asMap(
                "overwrite", false,
                "resource_type", "auto"
        );

        List<Map<String, Object>> results = new ArrayList<>();
        Arrays.stream(files).parallel().forEach(file -> {
            String filePath = String.format("images/gallery/%s", UUID.randomUUID());
            params.put("public_id", filePath);
            try {
                Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), params);
                log.info("uploaded {} done at {}", filePath, LocalDateTime.now());
                results.add(result);
            } catch (IOException e) {
                log.error("uploaded failed for file {}", file.getOriginalFilename());
            }
        });

        return results.stream().map(this::mapUploadResponseToVM).collect(Collectors.toList());
    }

    /**
     * Convert upload result to VM
     *
     * @param response upload response
     * @return upload VM
     */
    private UploadImageVM mapUploadResponseToVM(Map<String, Object> response) {

        UploadImageVM vm = new UploadImageVM();
        vm.setUrl(response.getOrDefault("url", "").toString());
        vm.setSecuredUrl(response.getOrDefault("secure_url", "").toString());
        vm.setPublicId(response.getOrDefault("public_id", "").toString());
        vm.setFormat(response.getOrDefault("format", "").toString());
        vm.setWidth(Integer.valueOf(String.valueOf(response.getOrDefault("width", 0))));
        vm.setHeight(Integer.valueOf(String.valueOf(response.getOrDefault("height", 0))));
        vm.setCreatedAt(LocalDateTime.now());
        return vm;
    }

    /**
     * Setter for cloudinary.
     */
    @Autowired
    public void setCloudinary(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
}
