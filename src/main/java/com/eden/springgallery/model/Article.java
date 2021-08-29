package com.eden.springgallery.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Article model
 */
@Document
@Getter
@Setter
public class Article extends BaseModel {

    private String title;
    private String caption;
    private String author;
    private List<Image> images;
}
