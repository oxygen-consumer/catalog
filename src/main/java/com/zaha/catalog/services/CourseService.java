package com.zaha.catalog.services;

import com.zaha.catalog.domain.dto.CourseDto;
import com.zaha.catalog.domain.entities.CourseEntity;
import com.zaha.catalog.mappers.Mapper;
import com.zaha.catalog.mappers.impl.TeacherMapper;
import com.zaha.catalog.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final Mapper<CourseEntity, CourseDto> courseMapper;
    private final TeacherMapper teacherMapper;
    private final TeacherService teacherService;


    public CourseService(CourseRepository courseRepository, Mapper<CourseEntity, CourseDto> courseMapper, TeacherMapper teacherMapper, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
        this.teacherService = teacherService;
    }

    /*
    CREATE
     */
    public CourseDto createCourse(CourseDto courseDto) {
        CourseEntity courseEntity = courseMapper.mapFrom(courseDto);
        return courseMapper.mapTo(courseRepository.save(courseEntity));
    }

    /*
    READ
     */
    public List<CourseDto> getAllCourses() {
        return StreamSupport.stream(courseRepository
                                .findAll()
                                .spliterator(),
                        true
                )
                .map(courseMapper::mapTo)
                .toList();
    }

    public Optional<CourseDto> getCourseById(Long id) {
        return courseRepository.findById(id).map(courseMapper::mapTo);
    }

    public boolean exists(Long id) {
        return courseRepository.existsById(id);
    }

    /*
    UPDATE
     */
    public CourseDto updateCourse(CourseDto courseDto) {
        CourseEntity courseEntity = courseMapper.mapFrom(courseDto);
        return courseMapper.mapTo(courseRepository.save(courseEntity));
    }

    public CourseDto patchCourse(Long id, CourseDto courseDto) {
        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow();
        courseEntity.setName(courseDto.getName());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setStartDate(courseDto.getStartDate());
        courseEntity.setEndDate(courseDto.getEndDate());
        if (teacherService.exists(courseDto.getTeacher().getId())) {
            courseEntity.setTeacher(teacherMapper.mapFrom(courseDto.getTeacher()));
        }
        return courseMapper.mapTo(courseRepository.save(courseEntity));
    }

    /*
    DELETE
     */
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
