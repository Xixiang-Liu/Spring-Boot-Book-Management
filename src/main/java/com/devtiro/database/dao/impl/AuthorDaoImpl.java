package com.devtiro.database.dao.impl;

import com.devtiro.database.dao.AuthorDao;
import com.devtiro.database.domain.AuthorJdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void  create(AuthorJdbc authorJdbc) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                authorJdbc.getId(), authorJdbc.getName(), authorJdbc.getAge()
        );
    }

    @Override
    public Optional<AuthorJdbc> findOne(long authorId) {
        List<AuthorJdbc> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(),
                authorId
        );

        return results.stream().findFirst();
    }

    public static class AuthorRowMapper implements RowMapper<AuthorJdbc> {

        @Override
        public AuthorJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AuthorJdbc.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

    @Override
    public List<AuthorJdbc> find() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
        );
    }

    @Override
    public void update(long id, AuthorJdbc authorJdbc) {
        jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                authorJdbc.getId(), authorJdbc.getName(), authorJdbc.getAge(), id
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(
                "DELETE FROM authors where id = ?",
                id
        );
    }
}
