package com.zaha.catalog.controllers;

import com.zaha.catalog.domain.dto.TeacherDto;
import com.zaha.catalog.services.AuditService;
import com.zaha.catalog.services.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;
    private final AuditService auditService;

    public TeacherController(TeacherService teacherService, AuditService auditService) {
        this.teacherService = teacherService;
        this.auditService = auditService;
    }

    /*
    CREATE
     */
    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "post_teacher");
        return new ResponseEntity<>(teacherService.createTeacher(teacherDto), HttpStatus.CREATED);
    }

    /*
    READ
     */
    @GetMapping("/teachers")
    public List<TeacherDto> listTeachers(HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_teachers");
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_teacher_" + id);
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    UPDATE
     */
    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "put_teacher_" + id);
        if (!teacherService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        teacherDto.setId(id);
        return ResponseEntity.ok(teacherService.updateTeacher(teacherDto));
    }

    @PatchMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> patchTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "patch_teacher_" + id);
        if (!teacherService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teacherService.patchTeacher(id, teacherDto));
    }

    /*
    DELETE
     */
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "delete_teacher_" + id);
        if (!teacherService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}