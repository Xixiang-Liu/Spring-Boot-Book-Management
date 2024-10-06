package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.BookDao;
import com.devtiro.database.domain.BookJdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(BookJdbc bookJdbc) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                bookJdbc.getIsbn(),
                bookJdbc.getTitle(),
                bookJdbc.getAuthorId()
        );
    }

    @Override
    public Optional<BookJdbc> findOne(String isbn) {
        List<BookJdbc> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );

        return results.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<BookJdbc> {

        @Override
        public BookJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
            return BookJdbc.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }

    @Override
    public List<BookJdbc> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id from books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(String isbn, BookJdbc bookJdbc) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                bookJdbc.getIsbn(), bookJdbc.getTitle(), bookJdbc.getAuthorId(), isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books where isbn = ?",
                isbn
        );
    }
}
