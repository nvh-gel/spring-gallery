package com.example.springgallery.repository;

import com.example.springgallery.model.Article;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

/**
 * Reposiory for article.
 */
@Repository
public interface ArticleRepository extends SoftDeleteCrudRepository<Article, ObjectId> {
}
