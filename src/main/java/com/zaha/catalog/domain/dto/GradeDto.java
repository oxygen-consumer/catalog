package com.zaha.catalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDto {

    private Long id;

    private Integer gradeValue;

    private CourseDto course;

    private StudentDto student;

}
