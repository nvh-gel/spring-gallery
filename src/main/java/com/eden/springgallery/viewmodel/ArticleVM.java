package com.eden.springgallery.viewmodel;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * View model for article
 */
@Data
public class ArticleVM {

    private Long id;
    private String title;
    private String caption;
    private String author;
    private List<ImageVM> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
