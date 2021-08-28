package com.example.springgallery.service;

import com.example.springgallery.viewmodel.ArticleVM;

import java.util.List;

public interface ArticleService {

    ArticleVM createArticle(ArticleVM articleVM);

    List<ArticleVM> getAllArticles();

    List<ArticleVM> getArticlesByPaging(int page, int limit, String sortBy, String order);

    ArticleVM getArticleById(String id);

    ArticleVM updateArticle(ArticleVM articleVM);
}
