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
public class TeacherDto {

    private Long id;

    private String name;

    private Date birthDate;

    private String email;

    private Set<CourseDto> courses;

}
