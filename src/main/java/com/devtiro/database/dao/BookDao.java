package com.devtiro.database.dao;

import com.devtiro.database.domain.BookJdbc;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void create(BookJdbc bookJdbc);

    Optional<BookJdbc> findOne(String isbn);

    List<BookJdbc> find();

    void update(String isbn, BookJdbc bookJdbc);

    void delete(String isbn);
}
