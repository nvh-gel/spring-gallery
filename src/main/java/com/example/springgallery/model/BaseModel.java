package com.example.springgallery.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Base database model.
 */
@Getter
@Setter
public class BaseModel {

    @Id
    private ObjectId id;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
