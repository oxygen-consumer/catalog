package com.zaha.catalog.services;

import com.zaha.catalog.domain.dto.CourseDto;
import com.zaha.catalog.domain.entities.CourseEntity;
import com.zaha.catalog.mappers.Mapper;
import com.zaha.catalog.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final Mapper<CourseEntity, CourseDto> courseMapper;


    public CourseService(CourseRepository courseRepository, Mapper<CourseEntity, CourseDto> courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

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

}
