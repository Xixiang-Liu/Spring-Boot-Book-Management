package com.devtiro.database;

import com.devtiro.database.domain.dto.AuthorDto;
import com.devtiro.database.domain.dto.BookDto;
import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.domain.entities.BookJpaEntity;

public final class TestDataUtilJpa {
    private TestDataUtilJpa() {

    }

    public static AuthorJpaEntity createTestAuthorA() {
        return AuthorJpaEntity.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorJpaEntity createTestAuthorB() {
        return AuthorJpaEntity.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static AuthorJpaEntity createTestAuthorC() {
        return AuthorJpaEntity.builder()
                .id(3L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static BookJpaEntity createTestBookEntityA(final AuthorJpaEntity authorJpaEntity) {
        return BookJpaEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorJpaEntity(authorJpaEntity)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static BookJpaEntity createTestBookB(final AuthorJpaEntity authorJpaEntity) {
        return BookJpaEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond the Horizon")
                .authorJpaEntity(authorJpaEntity)
                .build();
    }

    public static BookJpaEntity createTestBookC(final AuthorJpaEntity authorJpaEntity) {
        return BookJpaEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .authorJpaEntity(authorJpaEntity)
                .build();
    }
}
