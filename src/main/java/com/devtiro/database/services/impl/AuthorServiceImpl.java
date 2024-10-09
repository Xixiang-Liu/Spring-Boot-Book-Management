package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.repositories.AuthorJpaRepository;
import com.devtiro.database.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorJpaRepository authorJpaRepository;

    public AuthorServiceImpl(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public AuthorJpaEntity save(AuthorJpaEntity authorEntity) {
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

    @Override
    public Optional<AuthorJpaEntity> findOne(Long id) {
        return authorJpaRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorJpaRepository.existsById(id);
    }

    @Override
    public AuthorJpaEntity partialUpdate(Long id, AuthorJpaEntity authorEntity) {
        authorEntity.setId(id);

        return authorJpaRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorJpaRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }
}
