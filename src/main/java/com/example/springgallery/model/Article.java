package com.example.springgallery.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Article extends BaseModel {

    private String name;
    private String author;
}
