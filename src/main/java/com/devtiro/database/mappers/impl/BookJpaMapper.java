package com.devtiro.database.mappers.impl;

import com.devtiro.database.domain.dto.BookDto;
import com.devtiro.database.domain.entities.BookJpaEntity;
import com.devtiro.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookJpaMapper implements Mapper<BookJpaEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookJpaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookJpaEntity book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookJpaEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookJpaEntity.class);
    }
}
