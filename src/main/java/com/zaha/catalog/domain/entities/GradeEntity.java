package com.zaha.catalog.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeEntity {

    private Long id;

    private double gradeValue;

    private CourseEntity course;

    private StudentEntity student;

}
