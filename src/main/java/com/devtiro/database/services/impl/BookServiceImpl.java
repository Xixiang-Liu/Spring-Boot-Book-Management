package com.devtiro.database.services.impl;

import com.devtiro.database.domain.entities.BookJpaEntity;
import com.devtiro.database.repositories.AuthorJpaRepository;
import com.devtiro.database.repositories.BookJpaRepository;
import com.devtiro.database.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorJpaRepository authorJpaRepository;
    private BookJpaRepository bookJpaRepository;

    public BookServiceImpl(BookJpaRepository bookJpaRepository, AuthorJpaRepository authorJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public BookJpaEntity createUpdateBook(String isbn, BookJpaEntity book) {
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
    public Page<BookJpaEntity> findAll(Pageable pageable) {
        return bookJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<BookJpaEntity> findOne(String isbn) {
        return bookJpaRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookJpaRepository.existsById(isbn);
    }

    @Override
    public BookJpaEntity partialUpdate(String isbn, BookJpaEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookJpaRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookJpaRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(String isbn) {
        bookJpaRepository.deleteById(isbn);
    }
}
