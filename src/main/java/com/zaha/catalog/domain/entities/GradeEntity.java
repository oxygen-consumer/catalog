package com.zaha.catalog.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "grades")
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_id_seq")
    private Long id;

    @SQLRestriction("gradeValue >= 1 AND gradeValue <= 10")
    private double gradeValue;

    @ManyToOne
    private CourseEntity course;

    @ManyToOne
    private StudentEntity student;

}
