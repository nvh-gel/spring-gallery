package com.example.springgallery.mapper;

import com.example.springgallery.model.Article;
import com.example.springgallery.viewmodel.ArticleVM;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

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
    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    ArticleVM toArticleVM(Article article);

    /**
     * Map an article view model to model
     *
     * @param articleVM vm to map
     * @return article model
     */
    @Mapping(target = "id", source = "id", qualifiedByName = "stringToObjectId")
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
     * @param article updating data
     */
    void mapUpdateArticle(@MappingTarget Article existing, Article article);

    /**
     * Customer mapper for object id.
     *
     * @param objectId object id to map
     * @return object id string
     */
    @Named("objectIdToString")
    static String objectIdToString(ObjectId objectId) {

        return objectId.toString();
    }

    /**
     * Custom mapper for string to object id
     *
     * @param str id string
     * @return object id
     */
    @Named("stringToObjectId")
    static ObjectId stringToObjectId(String str) {

        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return new ObjectId(str);
    }
}
