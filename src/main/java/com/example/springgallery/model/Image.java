package com.example.springgallery.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Data model for image.
 */
@Getter
@Setter
public class Image {

    private String url;
    private String secureUrl;
    private String publicId;
}
