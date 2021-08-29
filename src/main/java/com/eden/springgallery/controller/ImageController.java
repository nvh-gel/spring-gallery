package com.eden.springgallery.controller;

import com.eden.springgallery.viewmodel.UploadImageVM;
import com.eden.springgallery.service.ImageService;
import com.eden.springgallery.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller for images.
 */
@RestController
@RequestMapping("images")
public class ImageController {

    private ImageService imageService;

    /**
     * Upload images
     *
     * @param files images to upload
     * @return upload result
     */
    @PostMapping("/upload")
    public ResponseEntity<ResponseModel> uploadImage(@RequestBody MultipartFile... files) {

        List<UploadImageVM> result = imageService.uploadImage(files);
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));

    }

    /**
     * Setter for imageService.
     */
    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
