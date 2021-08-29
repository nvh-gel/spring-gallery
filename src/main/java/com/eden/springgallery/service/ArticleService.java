package com.eden.springgallery.service;

import com.eden.springgallery.viewmodel.ArticleVM;

import java.util.List;

/**
 * Service for managing article
 */
public interface ArticleService {

    /**
     * Create article
     *
     * @param articleVM article data to create
     * @return created article
     */
    ArticleVM createArticle(ArticleVM articleVM);

    /**
     * Send message to queue to create an article.
     *
     * @param articleVM article data to create
     */
    void sendArticleOnQueue(ArticleVM articleVM);

    /**
     * Get all articles
     *
     * @return list of articles
     */
    List<ArticleVM> getAllArticles();

    /**
     * Get articles with paging
     *
     * @param page   page number
     * @param limit  page size
     * @param sortBy field to sort
     * @param order  order ASC/DESC
     * @return list of articles
     */
    List<ArticleVM> getArticlesByPaging(int page, int limit, String sortBy, String order);

    /**
     * Get single article by id.
     *
     * @param id id to search
     * @return article if found, or else , null
     */
    ArticleVM getArticleById(String id);

    /**
     * Update article
     *
     * @param articleVM article data to update
     * @return updated article
     */
    ArticleVM updateArticle(ArticleVM articleVM);

    /**
     * Soft delete an article.
     *
     * @param id article id to delete
     * @return deleted article
     */
    ArticleVM deleteArticle(String id);

    /**
     * Hard delete an article.
     *
     * @param id article id to remove
     * @return removed article
     */
    ArticleVM hardDeleteArticle(String id);
}
