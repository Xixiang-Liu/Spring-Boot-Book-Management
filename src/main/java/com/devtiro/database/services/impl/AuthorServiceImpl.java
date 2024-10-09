package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.repositories.AuthorJpaRepository;
import com.devtiro.database.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AuthorJpaEntity> findAll() {
        return StreamSupport.stream(authorJpaRepository
                    .findAll()
                    .spliterator(),
                    false)
                .collect(Collectors.toList());
    }
}
