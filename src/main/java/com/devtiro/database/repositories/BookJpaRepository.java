package com.devtiro.database.repositories;

import com.devtiro.database.domain.entities.BookJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends CrudRepository<BookJpaEntity, String>,
        PagingAndSortingRepository<BookJpaEntity, String> {
}
