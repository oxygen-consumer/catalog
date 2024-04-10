package com.zaha.catalog.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    private Long id;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

}
