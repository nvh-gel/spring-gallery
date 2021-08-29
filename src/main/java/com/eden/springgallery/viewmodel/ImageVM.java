package com.eden.springgallery.viewmodel;

import lombok.Data;

/**
 * View Model for image.
 */
@Data
public class ImageVM {

    private String url;
    private String secureUrl;
    private String publicId;
}
