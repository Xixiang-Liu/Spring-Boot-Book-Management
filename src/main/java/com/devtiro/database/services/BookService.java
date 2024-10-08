package com.devtiro.database.services;

import com.devtiro.database.domain.entities.BookJpaEntity;

public interface BookService {

    BookJpaEntity createBook(String isbn, BookJpaEntity book);

}
