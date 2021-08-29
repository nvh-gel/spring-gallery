package com.eden.springgallery.repository;

import com.eden.springgallery.model.Article;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

/**
 * Reposiory for article.
 */
@Repository
public interface ArticleRepository extends SoftDeleteCrudRepository<Article, ObjectId> {
}
