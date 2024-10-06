package com.devtiro.database.dao.impl;

import com.devtiro.database.TestDataUtil;
import com.devtiro.database.dao.AuthorDao;
import com.devtiro.database.domain.AuthorJdbc;
import com.devtiro.database.domain.BookJdbc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookJdbcDaoImplIntegrationTests {

    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookJdbcDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorJdbc authorJdbc = TestDataUtil.createTestAuthorA();
        authorDao.create(authorJdbc);
        BookJdbc bookJdbc = TestDataUtil.createTestBookA();
        bookJdbc.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbc);
        Optional<BookJdbc> result = underTest.findOne(bookJdbc.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookJdbc);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorJdbc authorJdbc = TestDataUtil.createTestAuthorA();
        authorDao.create(authorJdbc);

        BookJdbc bookJdbcA = TestDataUtil.createTestBookA();
        bookJdbcA.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbcA);

        BookJdbc bookJdbcB = TestDataUtil.createTestBookB();
        bookJdbcB.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbcB);

        BookJdbc bookJdbcC = TestDataUtil.createTestBookC();
        bookJdbcC.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbcC);

        List<BookJdbc> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .contains(bookJdbcA, bookJdbcB, bookJdbcC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorJdbc authorJdbc = TestDataUtil.createTestAuthorA();
        authorDao.create(authorJdbc);

        BookJdbc bookJdbcA = TestDataUtil.createTestBookA();
        bookJdbcA.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbcA);

        bookJdbcA.setTitle("UPDATED");
        underTest.update(bookJdbcA.getIsbn(), bookJdbcA);

        Optional<BookJdbc> result = underTest.findOne(bookJdbcA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookJdbcA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorJdbc authorJdbc = TestDataUtil.createTestAuthorA();
        authorDao.create(authorJdbc);

        BookJdbc bookJdbcA = TestDataUtil.createTestBookA();
        bookJdbcA.setAuthorId(authorJdbc.getId());
        underTest.create(bookJdbcA);

        underTest.delete(bookJdbcA.getIsbn());

        Optional<BookJdbc> result = underTest.findOne(bookJdbcA.getIsbn());
        assertThat(result).isEmpty();
    }
}
