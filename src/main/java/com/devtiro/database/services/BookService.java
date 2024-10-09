package com.devtiro.database.services;

import com.devtiro.database.domain.entities.BookJpaEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookJpaEntity createUpdateBook(String isbn, BookJpaEntity book);

    List<BookJpaEntity> findAll();

    Optional<BookJpaEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookJpaEntity partialUpdate(String isbn, BookJpaEntity bookEntity);
}
