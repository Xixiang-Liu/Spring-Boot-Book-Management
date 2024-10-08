package com.devtiro.database.repositories;

import com.devtiro.database.TestDataUtilJpa;
import com.devtiro.database.dao.impl.AuthorDaoImpl;
import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.domain.entities.BookJpaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookJpaEntityRepositoryIntegrationTests {

    private BookJpaRepository underTest;
    @Autowired
    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    public BookJpaEntityRepositoryIntegrationTests(BookJpaRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorJpaEntity authorJpaEntity = TestDataUtilJpa.createTestAuthorA();
        BookJpaEntity bookJpaEntity = TestDataUtilJpa.createTestBookA(authorJpaEntity);
        underTest.save(bookJpaEntity);
        Optional<BookJpaEntity> result = underTest.findById(bookJpaEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookJpaEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorJpaEntity authorJpaEntity = TestDataUtilJpa.createTestAuthorA();

        BookJpaEntity bookJpaEntityA = TestDataUtilJpa.createTestBookA(authorJpaEntity);
        underTest.save(bookJpaEntityA);
        BookJpaEntity bookJpaEntityB = TestDataUtilJpa.createTestBookB(authorJpaEntity);
        underTest.save(bookJpaEntityB);
        BookJpaEntity bookJpaEntityC = TestDataUtilJpa.createTestBookC(authorJpaEntity);
        underTest.save(bookJpaEntityC);

        Iterable<BookJpaEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookJpaEntityA, bookJpaEntityB, bookJpaEntityC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorJpaEntity authorJpaEntity = TestDataUtilJpa.createTestAuthorA();

        BookJpaEntity bookJpaEntityA = TestDataUtilJpa.createTestBookA(authorJpaEntity);
        underTest.save(bookJpaEntityA);

        bookJpaEntityA.setTitle("UPDATED");
        underTest.save(bookJpaEntityA);

        Optional<BookJpaEntity> result = underTest.findById(bookJpaEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookJpaEntityA);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorJpaEntity authorJpaEntity = TestDataUtilJpa.createTestAuthorA();

        BookJpaEntity bookJpaEntityA = TestDataUtilJpa.createTestBookA(authorJpaEntity);
        underTest.save(bookJpaEntityA);

        underTest.deleteById(bookJpaEntityA.getIsbn());

        Optional<BookJpaEntity> result = underTest.findById(bookJpaEntityA.getIsbn());
        assertThat(result).isEmpty();
    }
}
