package com.devtiro.database.repositories;

import com.devtiro.database.TestDataUtilJdbc;
import com.devtiro.database.TestDataUtilJpa;
import com.devtiro.database.dao.AuthorDao;
import com.devtiro.database.dao.impl.BookDaoImpl;
import com.devtiro.database.domain.AuthorJdbc;
import com.devtiro.database.domain.AuthorJpa;
import com.devtiro.database.domain.BookJdbc;
import com.devtiro.database.domain.BookJpa;
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
public class BookJpaRepositoryIntegrationTests {

    private BookJpaRepository underTest;

    @Autowired
    public BookJpaRepositoryIntegrationTests(BookJpaRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorJpa authorJpa = TestDataUtilJpa.createTestAuthorA();
        BookJpa bookJpa = TestDataUtilJpa.createTestBookA(authorJpa);
        underTest.save(bookJpa);
        Optional<BookJpa> result = underTest.findById(bookJpa.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookJpa);
    }

//    @Test
//    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
//        AuthorJdbc authorJdbc = TestDataUtilJdbc.createTestAuthorA();
//        authorDao.create(authorJdbc);
//
//        BookJdbc bookJdbcA = TestDataUtilJdbc.createTestBookA();
//        bookJdbcA.setAuthorId(authorJdbc.getId());
//        underTest.create(bookJdbcA);
//
//        BookJdbc bookJdbcB = TestDataUtilJdbc.createTestBookB();
//        bookJdbcB.setAuthorId(authorJdbc.getId());
//        underTest.create(bookJdbcB);
//
//        BookJdbc bookJdbcC = TestDataUtilJdbc.createTestBookC();
//        bookJdbcC.setAuthorId(authorJdbc.getId());
//        underTest.create(bookJdbcC);
//
//        List<BookJdbc> result = underTest.find();
//        assertThat(result)
//                .hasSize(3)
//                .contains(bookJdbcA, bookJdbcB, bookJdbcC);
//    }
//
//    @Test
//    public void testThatBookCanBeUpdated() {
//        AuthorJdbc authorJdbc = TestDataUtilJdbc.createTestAuthorA();
//        authorDao.create(authorJdbc);
//
//        BookJdbc bookJdbcA = TestDataUtilJdbc.createTestBookA();
//        bookJdbcA.setAuthorId(authorJdbc.getId());
//        underTest.create(bookJdbcA);
//
//        bookJdbcA.setTitle("UPDATED");
//        underTest.update(bookJdbcA.getIsbn(), bookJdbcA);
//
//        Optional<BookJdbc> result = underTest.findOne(bookJdbcA.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(bookJdbcA);
//    }
//
//    @Test
//    public void testThatBookCanBeDeleted() {
//        AuthorJdbc authorJdbc = TestDataUtilJdbc.createTestAuthorA();
//        authorDao.create(authorJdbc);
//
//        BookJdbc bookJdbcA = TestDataUtilJdbc.createTestBookA();
//        bookJdbcA.setAuthorId(authorJdbc.getId());
//        underTest.create(bookJdbcA);
//
//        underTest.delete(bookJdbcA.getIsbn());
//
//        Optional<BookJdbc> result = underTest.findOne(bookJdbcA.getIsbn());
//        assertThat(result).isEmpty();
//    }
}
