package com.example.springgallery.repository;

import com.example.springgallery.model.Article;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends SoftDeleteCrudRepository<Article, ObjectId> {
}
