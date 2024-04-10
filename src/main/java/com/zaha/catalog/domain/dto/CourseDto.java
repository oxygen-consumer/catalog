package com.zaha.catalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

        private Long id;

        private String name;

        private String description;

        private Date startDate;

        private Date endDate;

        private TeacherDto teacher;

        private Set<StudentDto> enrolledStudents;

}
