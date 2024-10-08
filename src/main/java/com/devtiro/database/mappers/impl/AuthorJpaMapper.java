package com.devtiro.database.mappers.impl;

import com.devtiro.database.domain.dto.AuthorDto;
import com.devtiro.database.domain.entities.AuthorJpaEntity;
import com.devtiro.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorJpaMapper implements Mapper<AuthorJpaEntity, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorJpaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorJpaEntity authorJpaEntity) {
        return modelMapper.map(authorJpaEntity, AuthorDto.class);
    }

    @Override
    public AuthorJpaEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorJpaEntity.class);
    }
}
