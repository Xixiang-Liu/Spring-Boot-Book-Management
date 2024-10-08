package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.repositories.AuthorJpaRepository;
import com.devtiro.database.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorJpaRepository authorJpaRepository;

    public AuthorServiceImpl(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public AuthorJpaEntity createAuthor(AuthorJpaEntity authorEntity) {
        return authorJpaRepository.save(authorEntity);
    }
}
