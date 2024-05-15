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

    /*
    CREATE
     */
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);
        return studentMapper.mapTo(studentRepository.save(studentEntity));
    }

    /*
    READ
     */
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

    public boolean exists(Long id) {
        return studentRepository.existsById(id);
    }

    /*
    UPDATE
     */

    public StudentDto updateStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);
        return studentMapper.mapTo(studentRepository.save(studentEntity));
    }

    public StudentDto patchStudent(Long id, StudentDto studentDto) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow();
        studentEntity.setName(studentDto.getName());
        studentEntity.setBirthDate(studentDto.getBirthDate());
        studentEntity.setEmail(studentDto.getEmail());
        return studentMapper.mapTo(studentRepository.save(studentEntity));
    }

    /*
    DELETE
     */
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
