package com.eden.springgallery.service;

import com.eden.springgallery.viewmodel.UploadImageVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service to manage images
 */
public interface ImageService {

    /**
     * Upload images to cloud
     *
     * @param files files to upload
     * @return upload result
     */
    List<UploadImageVM> uploadImage(MultipartFile... files);
}
