package com.eden.springgallery.repository;

import com.eden.springgallery.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface SoftDeleteCrudRepository<T extends BaseModel, I extends Long> extends JpaRepository<T, I> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDeleted = false")
    @NonNull
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.isDeleted = false")
    @NonNull
    Optional<T> findById(@NonNull I id);

    //Look up deleted entities
    @Query("select e from #{#entityName} e where e.isDeleted = true")
    @Transactional(readOnly = true)
    List<T> findInactive();

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.isDeleted = false")
    long count();

    @Override
    @Transactional(readOnly = true)
    default boolean existsById(@NonNull I id) {
        return findById(id).isPresent();
    }

    @Override
    @Query("update #{#entityName} e set e.isDeleted=true where e.id = ?1")
    @Transactional
    @Modifying
    void deleteById(@NonNull Long id);


    @Override
    @Transactional
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(entity -> deleteById(entity.getId()));
    }

    @Override
    @Query("update #{#entityName} e set e.isDeleted=true")
    @Transactional
    @Modifying
    void deleteAll();

    @Query("select e from #{#entityName} e where e.id = ?1")
    @Transactional(readOnly = true)
    @NonNull
    Optional<T> findToRemove(I id);
}
