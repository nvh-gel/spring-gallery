package com.eden.springgallery.repository;

import com.eden.springgallery.model.BaseModel;
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

/**
 * Base repository with soft deletion.
 *
 * @param <T> Model type
 * @param <I> Key type
 */
@NoRepositoryBean
public interface SoftDeleteCrudRepository<T extends BaseModel, I extends ObjectId> extends MongoRepository<T, I> {

    /**
     * Find all records.
     *
     * @return list of records
     */
    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false }")
    @NonNull
    List<T> findAll();

    /**
     * Find records by paging.
     *
     * @param pageable paging information
     * @return list of records
     */
    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false }")
    @NonNull
    Page<T> findAll(@NonNull Pageable pageable);

    /**
     * Find single record by id
     *
     * @param i id to search
     * @return optional of found record
     */
    @Override
    @Transactional(readOnly = true)
    @Query("{ isDeleted : false , id : ?0}")
    @NonNull
    Optional<T> findById(@NonNull I i);

    /**
     * Find record by id, included soft deleted.
     *
     * @param i id to search
     * @return optional of found record
     */
    @Transactional(readOnly = true)
    @Query("{ id : ?0}")
    @NonNull
    Optional<T> findAllById(@NonNull I i);
}
