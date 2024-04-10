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
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_id_seq")
    private Long id;

    @SQLRestriction("value >= 1 AND value <= 10")
    private double value;

    @ManyToOne
    private CourseEntity course;

    @ManyToOne
    private StudentEntity student;

}
