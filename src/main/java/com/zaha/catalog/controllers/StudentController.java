package com.zaha.catalog.controllers;

import com.zaha.catalog.domain.dto.StudentDto;
import com.zaha.catalog.services.AuditService;
import com.zaha.catalog.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final AuditService auditService;

    public StudentController(StudentService studentService, AuditService auditService) {
        this.studentService = studentService;
        this.auditService = auditService;
    }

    /*
    CREATE
     */
    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "post_student");
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    /*
    READ
     */
    @GetMapping("/students")
    public List<StudentDto> listStudents(HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_students");
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_student_" + id);
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    UPDATE
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "put_student_" + id);
        if (!studentService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        studentDto.setId(id);
        return ResponseEntity.ok(studentService.updateStudent(studentDto));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id, @RequestBody StudentDto studentDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "patch_student_" + id);
        if (!studentService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentService.patchStudent(id, studentDto));
    }

    /*
    DELETE
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "delete_student_" + id);
        if (!studentService.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}