package com.example.springgallery.mapper;

import com.example.springgallery.model.Article;
import com.example.springgallery.viewmodel.ArticleVM;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    ArticleVM toArticleVM(Article article);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringToObjectId")
    Article toArticle(ArticleVM articleVM);

    List<ArticleVM> toArticleVM(List<Article> articles);

    void mapUpdateArticle(@MappingTarget Article existing, Article article);

    @Named("objectIdToString")
    static String objectIdToString(ObjectId objectId) {

        return objectId.toString();
    }

    @Named("stringToObjectId")
    static ObjectId stringToObjectId(String str) {

        if (!StringUtils.hasLength(str)) {
            return null;
        }
        return new ObjectId(str);
    }
}
