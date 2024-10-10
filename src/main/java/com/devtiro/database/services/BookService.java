package com.devtiro.database.services;

import com.devtiro.database.domain.entities.BookJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookJpaEntity createUpdateBook(String isbn, BookJpaEntity book);

    List<BookJpaEntity> findAll();

    Page<BookJpaEntity> findAll(Pageable pageable);

    Optional<BookJpaEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookJpaEntity partialUpdate(String isbn, BookJpaEntity bookEntity);

    void delete(String isbn);
}
