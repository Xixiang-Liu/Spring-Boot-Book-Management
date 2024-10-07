package com.devtiro.database.repositories;

import com.devtiro.database.domain.AuthorJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends CrudRepository<AuthorJpa, Long> {
    Iterable<AuthorJpa> ageLessThan(int age);
}
