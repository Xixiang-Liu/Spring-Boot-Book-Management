package com.devtiro.database.services;

import com.devtiro.database.domain.entities.AuthorJpaEntity;

import java.util.List;

public interface AuthorService {
    AuthorJpaEntity createAuthor(AuthorJpaEntity author);

    List<AuthorJpaEntity> findAll();
}
