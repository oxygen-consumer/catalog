package com.zaha.catalog.services;

import com.zaha.catalog.domain.dto.StudentDto;
import com.zaha.catalog.domain.entities.StudentEntity;
import com.zaha.catalog.mappers.Mapper;
import com.zaha.catalog.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Mapper<StudentEntity, StudentDto> studentMapper;

    public StudentService(StudentRepository studentRepository, Mapper<StudentEntity, StudentDto> studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> getAllStudents() {
        return StreamSupport.stream(studentRepository
                                .findAll()
                                .spliterator(),
                        true
                )
                .map(studentMapper::mapTo)
                .toList();
    }

    public Optional<StudentDto> getStudentById(Long id) {
        return studentRepository.findById(id).map(studentMapper::mapTo);
    }

}
