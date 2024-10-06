package com.devtiro.database.repositories;

import com.devtiro.database.TestDataUtilJdbc;
import com.devtiro.database.TestDataUtilJpa;
import com.devtiro.database.domain.AuthorJdbc;
import com.devtiro.database.domain.AuthorJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorJpaRepositoryIntegrationTests {

    private AuthorJpaRepository underTest;

    @Autowired
    public AuthorJpaRepositoryIntegrationTests(AuthorJpaRepository underTest) {

        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorJpa authorJpa = TestDataUtilJpa.createTestAuthorA();
        underTest.save(authorJpa);
        Optional<AuthorJpa> result = underTest.findById(authorJpa.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorJpa);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorJpa authorJpaA = TestDataUtilJpa.createTestAuthorA();
        underTest.save(authorJpaA);
        AuthorJpa authorJpaB = TestDataUtilJpa.createTestAuthorB();
        underTest.save(authorJpaB);
        AuthorJpa authorJpaC = TestDataUtilJpa.createTestAuthorC();
        underTest.save(authorJpaC);

        Iterable<AuthorJpa> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorJpaA, authorJpaB, authorJpaC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorJpa authorJpaA = TestDataUtilJpa.createTestAuthorA();
        underTest.save(authorJpaA);
        authorJpaA.setName("UPDATED");
        underTest.save(authorJpaA);
        Optional<AuthorJpa> result = underTest.findById(authorJpaA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorJpaA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorJpa authorJpaA = TestDataUtilJpa.createTestAuthorA();
        underTest.save(authorJpaA);
        underTest.deleteById(authorJpaA.getId());
        Optional<AuthorJpa> result = underTest.findById(authorJpaA.getId());
        assertThat(result).isEmpty();
    }
}
