package com.example.springgallery.viewmodel;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVM {

    private String id;
    private String title;
    private String caption;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
