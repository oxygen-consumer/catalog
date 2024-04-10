package com.zaha.catalog.mappers.impl;

import com.zaha.catalog.domain.dto.GradeDto;
import com.zaha.catalog.domain.entities.GradeEntity;
import com.zaha.catalog.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper implements Mapper<GradeEntity, GradeDto> {

    private final ModelMapper modelMapper;

    public GradeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GradeDto mapTo(GradeEntity gradeEntity) {
        return modelMapper.map(gradeEntity, GradeDto.class);
    }

    @Override
    public GradeEntity mapFrom(GradeDto gradeDto) {
        return modelMapper.map(gradeDto, GradeEntity.class);
    }

}
