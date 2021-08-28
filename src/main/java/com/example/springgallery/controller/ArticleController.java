package com.example.springgallery.controller;

import com.example.springgallery.service.ArticleService;
import com.example.springgallery.utils.ResponseModel;
import com.example.springgallery.viewmodel.ArticleVM;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
