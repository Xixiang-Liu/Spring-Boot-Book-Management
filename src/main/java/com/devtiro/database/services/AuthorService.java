package com.devtiro.database.services;

import com.devtiro.database.domain.entities.AuthorJpaEntity;

public interface AuthorService {
    AuthorJpaEntity createAuthor(AuthorJpaEntity author);
}
