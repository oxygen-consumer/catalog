package com.zaha.catalog.controllers;

import com.zaha.catalog.domain.dto.CourseDto;
import com.zaha.catalog.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
    CREATE
     */
    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }

    /*
    READ
     */
    @GetMapping("/courses")
    public List<CourseDto> listCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    UPDATE
     */
    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        courseDto.setId(id);
        return ResponseEntity.ok(courseService.updateCourse(courseDto));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseDto> patchCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(courseService.patchCourse(id, courseDto));
    }

    /*
    DELETE
     */
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseDto> deleteCourse(@PathVariable Long id) {
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
