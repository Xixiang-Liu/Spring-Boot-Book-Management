package com.devtiro.database.repositories;

import com.devtiro.database.TestDataUtilJpa;
import com.devtiro.database.domain.entities.AuthorJpaEntity;
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
public class AuthorJpaEntityRepositoryIntegrationTests {

    private AuthorJpaRepository underTest;

    @Autowired
    public AuthorJpaEntityRepositoryIntegrationTests(AuthorJpaRepository underTest) {

        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorJpaEntity authorJpaEntity = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntity);
        Optional<AuthorJpaEntity> result = underTest.findById(authorJpaEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorJpaEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorJpaEntity authorJpaEntityA = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntityA);
        AuthorJpaEntity authorJpaEntityB = TestDataUtilJpa.createTestAuthorB();
        underTest.save(authorJpaEntityB);
        AuthorJpaEntity authorJpaEntityC = TestDataUtilJpa.createTestAuthorC();
        underTest.save(authorJpaEntityC);

        Iterable<AuthorJpaEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorJpaEntityA, authorJpaEntityB, authorJpaEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorJpaEntity authorJpaEntityA = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntityA);
        authorJpaEntityA.setName("UPDATED");
        underTest.save(authorJpaEntityA);
        Optional<AuthorJpaEntity> result = underTest.findById(authorJpaEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorJpaEntityA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorJpaEntity authorJpaEntityA = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntityA);
        underTest.deleteById(authorJpaEntityA.getId());
        Optional<AuthorJpaEntity> result = underTest.findById(authorJpaEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorJpaEntity authorJpaEntityA = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntityA);
        AuthorJpaEntity authorJpaEntityB = TestDataUtilJpa.createTestAuthorB();
        underTest.save(authorJpaEntityB);
        AuthorJpaEntity authorJpaEntityC = TestDataUtilJpa.createTestAuthorC();
        underTest.save(authorJpaEntityC);

        Iterable<AuthorJpaEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(authorJpaEntityB, authorJpaEntityC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorJpaEntity authorJpaEntityA = TestDataUtilJpa.createTestAuthorEntityA();
        underTest.save(authorJpaEntityA);
        AuthorJpaEntity authorJpaEntityB = TestDataUtilJpa.createTestAuthorB();
        underTest.save(authorJpaEntityB);
        AuthorJpaEntity authorJpaEntityC = TestDataUtilJpa.createTestAuthorC();
        underTest.save(authorJpaEntityC);

        Iterable<AuthorJpaEntity> result =  underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(authorJpaEntityA);
    }
}
