package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.BookJpaEntity;
import com.devtiro.database.repositories.BookJpaRepository;
import com.devtiro.database.services.BookService;
import org.springframework.stereotype.Service;

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
}
