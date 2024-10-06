package com.devtiro.database;

import com.devtiro.database.domain.AuthorJdbc;
import com.devtiro.database.domain.AuthorJpa;
import com.devtiro.database.domain.BookJdbc;
import com.devtiro.database.domain.BookJpa;

public final class TestDataUtilJpa {
    private TestDataUtilJpa() {

    }

    public static AuthorJpa createTestAuthorA() {
        return AuthorJpa.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorJpa createTestAuthorB() {
        return AuthorJpa.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static AuthorJpa createTestAuthorC() {
        return AuthorJpa.builder()
                .id(3L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static BookJpa createTestBookA(final AuthorJpa authorJpa) {
        return BookJpa.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorJpa(authorJpa)
                .build();
    }

    public static BookJpa createTestBookB(final AuthorJpa authorJpa) {
        return BookJpa.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorJpa(authorJpa)
                .build();
    }

    public static BookJpa createTestBookC(final AuthorJpa authorJpa) {
        return BookJpa.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .authorJpa(authorJpa)
                .build();
    }
}
