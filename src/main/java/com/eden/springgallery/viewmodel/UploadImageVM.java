package com.eden.springgallery.viewmodel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * View model for image upload
 */
@Data
public class UploadImageVM {

    private String url;
    private String securedUrl;
    private String publicId;
    private String format;
    private Integer width;
    private Integer height;
    private LocalDateTime createdAt;
}
