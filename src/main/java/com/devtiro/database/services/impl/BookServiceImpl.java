package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.BookJpaEntity;
import com.devtiro.database.repositories.BookJpaRepository;
import com.devtiro.database.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookJpaRepository bookJpaRepository;

    public BookServiceImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public BookJpaEntity createBook(String isbn, BookJpaEntity book) {
        book.setIsbn(isbn);
        return bookJpaRepository.save(book);
    }

    @Override
    public List<BookJpaEntity> findAll() {
        return StreamSupport
                .stream(
                        bookJpaRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookJpaEntity> findOne(String isbn) {
        return bookJpaRepository.findById(isbn);
    }
}
