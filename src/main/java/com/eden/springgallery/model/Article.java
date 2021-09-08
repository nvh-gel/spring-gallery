package com.eden.springgallery.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

/**
 * Article model
 */
@Entity
@Getter
@Setter
public class Article extends BaseModel {

    private String title;
    @Column(length = 1000)
    private String caption;
    private String author;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Image> images;
}
