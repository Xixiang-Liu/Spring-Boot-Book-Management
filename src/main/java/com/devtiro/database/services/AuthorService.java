package com.devtiro.database.services;

import com.devtiro.database.domain.entities.AuthorJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorJpaEntity save(AuthorJpaEntity author);

    List<AuthorJpaEntity> findAll();

    Optional<AuthorJpaEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorJpaEntity partialUpdate(Long id, AuthorJpaEntity authorEntity);
}
