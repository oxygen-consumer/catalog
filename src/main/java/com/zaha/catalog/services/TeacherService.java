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

    /*
    CREATE
     */
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        TeacherEntity teacherEntity = teacherMapper.mapFrom(teacherDto);
        return teacherMapper.mapTo(teacherRepository.save(teacherEntity));
    }

    /*
    READ
     */
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

    public boolean exists(Long id) {
        return teacherRepository.existsById(id);
    }

    /*
    UPDATE
     */
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        TeacherEntity teacherEntity = teacherMapper.mapFrom(teacherDto);
        return teacherMapper.mapTo(teacherRepository.save(teacherEntity));
    }

    public TeacherDto patchTeacher(Long id, TeacherDto teacherDto) {
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow();
        teacherEntity.setName(teacherDto.getName());
        teacherEntity.setBirthDate(teacherDto.getBirthDate());
        teacherEntity.setEmail(teacherDto.getEmail());
        return teacherMapper.mapTo(teacherRepository.save(teacherEntity));
    }

    /*
    DELETE
     */
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

}
