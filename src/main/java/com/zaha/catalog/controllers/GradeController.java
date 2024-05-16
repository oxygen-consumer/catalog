package com.zaha.catalog.controllers;

import com.zaha.catalog.domain.dto.GradeDto;
import com.zaha.catalog.services.AuditService;
import com.zaha.catalog.services.GradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final GradeService gradeService;
    private final AuditService auditService;

    public GradeController(GradeService gradeService, AuditService auditService) {
        this.gradeService = gradeService;
        this.auditService = auditService;
    }

    /*
    CREATE
     */
    @PostMapping("/grades")
    public ResponseEntity<GradeDto> createGrade(@RequestBody GradeDto gradeDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "post_grade");
        return new ResponseEntity<>(gradeService.createGrade(gradeDto), HttpStatus.CREATED);
    }

    /*
    READ
     */
    @GetMapping("/grades")
    public List<GradeDto> listGrades(HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_grades");
        return gradeService.getAllGrades();
    }

    @GetMapping("/grades/{id}")
    public ResponseEntity<GradeDto> getGrade(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "get_grade_" + id);
        return gradeService.getGradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    UPDATE
     */
    @PutMapping("/grades/{id}")
    public ResponseEntity<GradeDto> updateGrade(@PathVariable Long id, @RequestBody GradeDto gradeDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "put_grade_" + id);
        if (!gradeService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gradeService.updateGrade(gradeDto));
    }

    @PatchMapping("/grades/{id}")
    public ResponseEntity<GradeDto> patchGrade(@PathVariable Long id, @RequestBody GradeDto gradeDto, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "patch_grade_" + id);
        if (!gradeService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gradeService.patchGrade(gradeDto));
    }

    /*
    DELETE
     */
    @DeleteMapping("/grades/{id}")
    public ResponseEntity<GradeDto> deleteGrade(@PathVariable Long id, HttpServletRequest request) {
        auditService.log(request.getRemoteAddr(), "delete_grade_" + id);
        if (!gradeService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        gradeService.deleteGrade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}