package com.eden.springgallery.controller;

import com.eden.springgallery.service.ArticleService;
import com.eden.springgallery.utils.ResponseModel;
import com.eden.springgallery.viewmodel.ArticleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for article
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    /**
     * Create article
     *
     * @param articleVM article data
     * @return created article
     */
    @PostMapping
    public ResponseEntity<ResponseModel> createArticle(@RequestBody ArticleVM articleVM) {

        articleService.sendArticleOnQueue(articleVM);
        return ResponseEntity.accepted().body(ResponseModel.accepted(null));
    }

    /**
     * Get all articles.
     *
     * @return list of all articles
     */
    @GetMapping
    public ResponseEntity<ResponseModel> getAllArticles() {

        return ResponseEntity.ok(ResponseModel.success(articleService.getAllArticles()));
    }

    /**
     * Get all articles with paging
     *
     * @param page   page number
     * @param limit  page size
     * @param sortBy field to sort
     * @param order  order ASC/DESC
     * @return list of articles by page
     */
    @GetMapping("/{page}/{limit}")
    public ResponseEntity<ResponseModel> getArticlesByPaging(@PathVariable int page,
                                                             @PathVariable int limit,
                                                             @RequestParam(required = false) String sortBy,
                                                             @RequestParam(required = false) String order) {

        return ResponseEntity.ok(ResponseModel.success(articleService.getArticlesByPaging(page, limit, sortBy, order)));
    }

    /**
     * Get single article by id.
     *
     * @param id article id
     * @return found article
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getArticleById(@PathVariable Long id) {

        ArticleVM result = articleService.getArticleById(id);

        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseModel.notFound());
        }
        return ResponseEntity.ok(ResponseModel.success(result));
    }

    /**
     * Update article
     *
     * @param id        article id
     * @param articleVM article data to update
     * @return updated article
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateArticle(@PathVariable Long id, @RequestBody ArticleVM articleVM) {

        articleVM.setId(id);
        articleService.sendArticleOnQueue(articleVM);
        return ResponseEntity.accepted().body(ResponseModel.accepted(null));
    }

    /**
     * Soft delete an article
     *
     * @param id article id
     * @return deleted article
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteArticle(@PathVariable Long id) {

        ArticleVM result = articleService.deleteArticle(id);

        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseModel.unaccepted());
        }
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));
    }

    /**
     * Hard delete an article
     *
     * @param id article id
     * @return removed article
     */
    @DeleteMapping("/{id}/remove")
    public ResponseEntity<ResponseModel> hardDeleteArticle(@PathVariable Long id) {

        ArticleVM result = articleService.hardDeleteArticle(id);
        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseModel.unaccepted());
        }
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));
    }

    /**
     * Setter for articleService.
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
