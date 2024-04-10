package com.zaha.catalog.mappers.impl;

import com.zaha.catalog.domain.dto.StudentDto;
import com.zaha.catalog.domain.entities.StudentEntity;
import com.zaha.catalog.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class StudentMapper implements Mapper<StudentEntity, StudentDto> {

   private final ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
         this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto mapTo(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override

    public StudentEntity mapFrom(StudentDto studentDto) {
        return modelMapper.map(studentDto, StudentEntity.class);
    }

}
