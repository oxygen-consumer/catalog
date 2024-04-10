package com.zaha.catalog.services;

import com.zaha.catalog.domain.dto.TeacherDto;
import com.zaha.catalog.domain.entities.TeacherEntity;
import com.zaha.catalog.mappers.Mapper;
import com.zaha.catalog.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final Mapper<TeacherEntity, TeacherDto> teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, Mapper<TeacherEntity, TeacherDto> teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherDto> getAllTeachers() {
        return StreamSupport.stream(teacherRepository
                                .findAll()
                                .spliterator(),
                        true
                )
                .map(teacherMapper::mapTo)
                .toList();
    }

    public Optional<TeacherDto> getTeacherById(Long id) {
        return teacherRepository.findById(id).map(teacherMapper::mapTo);
    }

}
