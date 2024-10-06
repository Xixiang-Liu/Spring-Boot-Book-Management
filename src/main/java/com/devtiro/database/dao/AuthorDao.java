package com.devtiro.database.dao;

import com.devtiro.database.domain.AuthorJdbc;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(AuthorJdbc authorJdbc);

    Optional<AuthorJdbc> findOne(long authorId);

    List<AuthorJdbc> find();

    void update(long id, AuthorJdbc authorJdbc);

    void delete(long id);
}
