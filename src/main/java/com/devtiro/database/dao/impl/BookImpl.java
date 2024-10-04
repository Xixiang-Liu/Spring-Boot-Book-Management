package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.BookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
