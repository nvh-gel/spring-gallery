package com.eden.springgallery.service.impl;

import com.eden.springgallery.mapper.ArticleMapper;
import com.eden.springgallery.model.Article;
import com.eden.springgallery.producer.ArticleProducer;
import com.eden.springgallery.repository.ArticleRepository;
import com.eden.springgallery.service.ArticleService;
import com.eden.springgallery.viewmodel.ArticleVM;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of article service.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private final ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);
    private ArticleProducer articleProducer;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM createArticle(ArticleVM articleVM) {

        Article article = articleMapper.toArticle(articleVM);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article result = articleRepository.save(article);
        return articleMapper.toArticleVM(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendArticleOnQueue(ArticleVM articleVM) {

        articleProducer.send(articleVM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleVM> getAllArticles() {

        List<Article> articles = articleRepository.findAll();
        return articleMapper.toArticleVM(articles);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleVM getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return articleMapper.toArticleVM(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM updateArticle(ArticleVM articleVM) {
        Article article = articleMapper.toArticle(articleVM);
        Article existing = articleRepository.findById(articleVM.getId()).orElse(null);

        if (null != existing) {
            articleMapper.mapUpdateArticle(existing, article);
            existing.setUpdatedAt(LocalDateTime.now());
            Article updated = articleRepository.save(existing);
            return articleMapper.toArticleVM(updated);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM deleteArticle(Long id) {
        Article deleted = articleRepository.findById(id).orElse(null);
        if (null != deleted) {
            deleted.setDeleted(true);
            deleted.setUpdatedAt(LocalDateTime.now());
            articleRepository.save(deleted);
        }
        return articleMapper.toArticleVM(deleted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ArticleVM hardDeleteArticle(Long id) {
        Article deleted = articleRepository.findToRemove(id).orElse(null);
        if (null != deleted) {
            deleted.setDeleted(true);
            deleted.setUpdatedAt(LocalDateTime.now());
            articleRepository.deleteById(id);
        }
        return articleMapper.toArticleVM(deleted);
    }

    /**
     * Setter for articleRepository.
     */
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Setter for articleProducer.
     */
    @Autowired
    public void setArticleProducer(ArticleProducer articleProducer) {
        this.articleProducer = articleProducer;
    }
}
