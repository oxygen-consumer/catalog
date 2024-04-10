package com.zaha.catalog.mappers.impl;

import com.zaha.catalog.domain.dto.CourseDto;
import com.zaha.catalog.domain.entities.CourseEntity;
import com.zaha.catalog.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class CourseMapper implements Mapper<CourseEntity, CourseDto> {

    private final ModelMapper modelMapper;

    public CourseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto mapTo(CourseEntity courseEntity) {
        return modelMapper.map(courseEntity, CourseDto.class);
    }

    @Override
    public CourseEntity mapFrom(CourseDto courseDto) {
        return modelMapper.map(courseDto, CourseEntity.class);
    }

}
