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

//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
//        AuthorJdbc authorJdbcA = TestDataUtil.createTestAuthorA();
//        underTest.create(authorJdbcA);
//        AuthorJdbc authorJdbcB = TestDataUtil.createTestAuthorB();
//        underTest.create(authorJdbcB);
//        AuthorJdbc authorJdbcC = TestDataUtil.createTestAuthorC();
//        underTest.create(authorJdbcC);
//
//        List<AuthorJdbc> result = underTest.find();
//        assertThat(result)
//                .hasSize(3)
//                .contains(authorJdbcA, authorJdbcB, authorJdbcC);
//    }
//
//    @Test
//    public void testThatAuthorCanBeUpdated() {
//        AuthorJdbc authorJdbcA = TestDataUtil.createTestAuthorA();
//        underTest.create(authorJdbcA);
//        authorJdbcA.setName("UPDATED");
//        underTest.update(authorJdbcA.getId(), authorJdbcA);
//        Optional<AuthorJdbc> result = underTest.findOne(authorJdbcA.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorJdbcA);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted() {
//        AuthorJdbc authorJdbcA = TestDataUtil.createTestAuthorA();
//        underTest.create(authorJdbcA);
//        underTest.delete(authorJdbcA.getId());
//        Optional<AuthorJdbc> result = underTest.findOne(authorJdbcA.getId());
//        assertThat(result).isEmpty();
//    }
}
