package com.example.springgallery.service.impl;

import com.example.springgallery.mapper.ArticleMapper;
import com.example.springgallery.model.Article;
import com.example.springgallery.repository.ArticleRepository;
import com.example.springgallery.service.ArticleService;
import com.example.springgallery.viewmodel.ArticleVM;
import org.bson.types.ObjectId;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    @Override
    public ArticleVM createArticle(ArticleVM articleVM) {

        Article article = articleMapper.toArticle(articleVM);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article result = articleRepository.save(article);
        return articleMapper.toArticleVM(result);
    }

    @Override
    public List<ArticleVM> getAllArticles() {

        List<Article> articles = articleRepository.findAll();
        return articleMapper.toArticleVM(articles);
    }

    @Override
    public List<ArticleVM> getArticlesByPaging(int page, int limit, String sortBy, String order) {

        Sort sort = Sort.by(StringUtils.hasLength(sortBy) ? sortBy : "createdAt").ascending();

        if ("DESC".equals(order)) {
            sort = sort.descending();
        }

        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), limit, sort);

        List<Article> articles = articleRepository.findAll(pageable).toList();
        return articleMapper.toArticleVM(articles);
    }

    @Override
    public ArticleVM getArticleById(String id) {

        if (!ObjectId.isValid(id)) {
            return null;
        }

        ObjectId objectId = new ObjectId(id);
        Article article = articleRepository.findById(objectId).orElse(null);
        return articleMapper.toArticleVM(article);
    }

    @Override
    public ArticleVM updateArticle(ArticleVM articleVM) {

        if (!ObjectId.isValid(articleVM.getId())) {
            return null;
        }

        ObjectId id = new ObjectId(articleVM.getId());

        Article article = articleMapper.toArticle(articleVM);
        Article existing = articleRepository.findById(id).orElse(null);

        if (null != existing) {
            articleMapper.mapUpdateArticle(existing, article);
            existing.setUpdatedAt(LocalDateTime.now());
            Article updated = articleRepository.save(existing);
            return articleMapper.toArticleVM(updated);
        }
        return null;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
