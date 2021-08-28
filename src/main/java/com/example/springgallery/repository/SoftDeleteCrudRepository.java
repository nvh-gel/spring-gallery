package com.example.springgallery.repository;

import com.example.springgallery.model.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteCrudRepository<T extends BaseModel, I extends ObjectId> extends MongoRepository<T, I> {

    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false }")
    @NonNull
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false }")
    @NonNull
    Page<T> findAll(@NonNull Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false , id : ?0}")
    @NonNull
    Optional<T> findById(@NonNull I i);
}
