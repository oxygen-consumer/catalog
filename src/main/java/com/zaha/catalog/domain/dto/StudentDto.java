package com.zaha.catalog.domain.dto;

import com.zaha.catalog.domain.entities.GradeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long id;

    private String name;

    private Date birthDate;

    private String email;

    private Set<CourseDto> enrolledCourses;

    private List<GradeDto> grades;

}
