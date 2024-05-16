package com.zaha.catalog.controllers;

import com.zaha.catalog.domain.dto.CourseDto;
import com.zaha.catalog.services.AuditService;
import com.zaha.catalog.services.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final AuditService auditService;

    public CourseController(CourseService courseService, AuditService auditService) {
        this.courseService = courseService;
        this.auditService = auditService;
    }

    /*
    CREATE
     */
    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "post_course");
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }

    /*
    READ
     */
    @GetMapping("/courses")
    public List<CourseDto> listCourses(HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_courses");
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_course_" + id);
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    UPDATE
     */
    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "put_course_" + id);
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        courseDto.setId(id);
        return ResponseEntity.ok(courseService.updateCourse(courseDto));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseDto> patchCourse(@PathVariable Long id, @RequestBody CourseDto courseDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "patch_course_" + id);
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(courseService.patchCourse(id, courseDto));
    }

    /*
    DELETE
     */
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseDto> deleteCourse(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "delete_course_" + id);
        if (!courseService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}