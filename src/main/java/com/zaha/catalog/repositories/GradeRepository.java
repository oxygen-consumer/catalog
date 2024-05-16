package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.GradeEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GradeRepository {

    private final JdbcTemplate jdbcTemplate;

    public GradeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GradeEntity save(GradeEntity gradeEntity) {
        Long id = this.jdbcTemplate.queryForObject("SELECT nextval('grade_id_seq')", Long.class);
        gradeEntity.setId(id);
        jdbcTemplate.update("INSERT INTO grades (id, grade_value, course_id, student_id) VALUES (?, ?, ?, ?)",
                gradeEntity.getId(),
                gradeEntity.getGradeValue(),
                gradeEntity.getCourse().getId(),
                gradeEntity.getStudent().getId()
        );
        return gradeEntity;
    }

    public Iterable<GradeEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM grades", (rs, rowNum) -> GradeEntity.builder()
                .id(rs.getLong("id"))
                .gradeValue(rs.getDouble("grade_value"))
                .build());
    }

    public Optional<GradeEntity> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM grades WHERE id = ?", new Object[]{id}, (rs, rowNum) -> Optional.of(GradeEntity.builder()
                .id(rs.getLong("id"))
                .gradeValue(rs.getDouble("grade_value"))
                .build())).stream().findFirst().orElse(Optional.empty());
    }

    public boolean existsById(Long id) {
        return jdbcTemplate.query("SELECT * FROM grades WHERE id = ?", new Object[]{id}, (rs, rowNum) -> rs.getLong("id")).stream().findFirst().isPresent();
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM grades WHERE id = ?", id);
    }

}
