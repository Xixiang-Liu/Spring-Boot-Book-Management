package com.devtiro.database.services;

import com.devtiro.database.domain.entities.AuthorJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorJpaEntity createAuthor(AuthorJpaEntity author);

    List<AuthorJpaEntity> findAll();

    Optional<AuthorJpaEntity> findOne(Long id);

    AuthorJpaEntity save(AuthorJpaEntity testAuthorEntityA);
}
