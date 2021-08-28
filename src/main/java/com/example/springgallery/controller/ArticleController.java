package com.example.springgallery.controller;

import com.example.springgallery.service.ArticleService;
import com.example.springgallery.utils.ResponseModel;
import com.example.springgallery.viewmodel.ArticleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<ResponseModel> createArticle(@RequestBody ArticleVM articleVM) {

        return ResponseEntity.accepted().body(ResponseModel.accepted(articleService.createArticle(articleVM)));
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllArticles() {

        return ResponseEntity.ok(ResponseModel.success(articleService.getAllArticles()));
    }

    @GetMapping("/{page}/{limit}")
    public ResponseEntity<ResponseModel> getArticlesByPaging(@PathVariable int page,
                                                             @PathVariable int limit,
                                                             @RequestParam(required = false) String sortBy,
                                                             @RequestParam(required = false) String order) {

        return ResponseEntity.ok(ResponseModel.success(articleService.getArticlesByPaging(page, limit, sortBy, order)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getArticleById(@PathVariable String id) {

        ArticleVM result = articleService.getArticleById(id);

        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseModel.notFound());
        }
        return ResponseEntity.ok(ResponseModel.success(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateArticle(@PathVariable String id, @RequestBody ArticleVM articleVM) {

        articleVM.setId(id);
        ArticleVM result = articleService.updateArticle(articleVM);

        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseModel.unaccepted());
        }
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteArticle(@PathVariable String id) {

        ArticleVM result = articleService.deleteArticle(id);

        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseModel.unaccepted());
        }
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<ResponseModel> hardDeleteArticle(@PathVariable String id) {

        ArticleVM result = articleService.hardDeleteArticle(id);
        if (null == result) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseModel.unaccepted());
        }
        return ResponseEntity.accepted().body(ResponseModel.accepted(result));
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
