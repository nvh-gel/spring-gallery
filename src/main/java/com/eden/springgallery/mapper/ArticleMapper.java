package com.eden.springgallery.mapper;

import com.eden.springgallery.model.Article;
import com.eden.springgallery.viewmodel.ArticleVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapstruct mapper for article
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper {

    /**
     * Map an article to VM
     *
     * @param article article to map
     * @return article view model
     */
    ArticleVM toArticleVM(Article article);

    /**
     * Map an article view model to model
     *
     * @param articleVM vm to map
     * @return article model
     */
    @Mapping(target = "deleted", ignore = true)
    Article toArticle(ArticleVM articleVM);

    /**
     * Map list of article models to VMs
     *
     * @param articles models to map
     * @return list of article VMs
     */
    List<ArticleVM> toArticleVM(List<Article> articles);

    /**
     * Map updating data to a model
     *
     * @param existing model to update
     * @param article  updating data
     */
    void mapUpdateArticle(@MappingTarget Article existing, Article article);
}
