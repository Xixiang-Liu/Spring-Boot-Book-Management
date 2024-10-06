package com.devtiro.database;

import com.devtiro.database.domain.AuthorJdbc;
import com.devtiro.database.domain.BookJdbc;

public final class TestDataUtil {
    private TestDataUtil() {

    }

    public static AuthorJdbc createTestAuthorA() {
        return AuthorJdbc.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorJdbc createTestAuthorB() {
        return AuthorJdbc.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static AuthorJdbc createTestAuthorC() {
        return AuthorJdbc.builder()
                .id(3L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static BookJdbc createTestBookA() {
        return BookJdbc.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static BookJdbc createTestBookB() {
        return BookJdbc.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorId(1L)
                .build();
    }

    public static BookJdbc createTestBookC() {
        return BookJdbc.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .authorId(1L)
                .build();
    }
}
