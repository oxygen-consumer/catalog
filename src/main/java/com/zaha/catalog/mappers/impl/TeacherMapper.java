package com.zaha.catalog.mappers.impl;

import com.zaha.catalog.domain.dto.TeacherDto;
import com.zaha.catalog.domain.entities.TeacherEntity;
import com.zaha.catalog.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class TeacherMapper implements Mapper<TeacherEntity, TeacherDto> {

    private final ModelMapper modelMapper;

    public TeacherMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TeacherDto mapTo(TeacherEntity teacherEntity) {
        return modelMapper.map(teacherEntity, TeacherDto.class);
    }

    @Override
    public TeacherEntity mapFrom(TeacherDto teacherDto) {
        return modelMapper.map(teacherDto, TeacherEntity.class);
    }

}
