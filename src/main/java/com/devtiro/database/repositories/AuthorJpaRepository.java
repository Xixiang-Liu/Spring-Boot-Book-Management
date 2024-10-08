package com.devtiro.database.repositories;

import com.devtiro.database.domain.entities.AuthorJpaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends CrudRepository<AuthorJpaEntity, Long> {
    Iterable<AuthorJpaEntity> ageLessThan(int age);

    @Query("SELECT a from AuthorJpaEntity a where a.age > ?1")
    Iterable<AuthorJpaEntity> findAuthorsWithAgeGreaterThan(int age);
}
