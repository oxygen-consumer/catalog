package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.CourseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CourseEntity save(CourseEntity courseEntity) {
        Long id = this.jdbcTemplate.queryForObject("SELECT nextval('course_id_seq')", Long.class);
        courseEntity.setId(id);
        jdbcTemplate.update("INSERT INTO courses (id, name, description, start_date, end_date, teacher_id) VALUES (?, ?, ?, ?, ?, ?)",
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getDescription(),
                courseEntity.getStartDate(),
                courseEntity.getEndDate(),
                courseEntity.getTeacher().getId()
        );
        return courseEntity;
    }

    public Iterable<CourseEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM courses", (rs, rowNum) -> CourseEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build());
    }

    public Optional<CourseEntity> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE id = ?", new Object[]{id}, (rs, rowNum) -> Optional.of(CourseEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build())).stream().findFirst().orElse(Optional.empty());
    }

    public boolean existsById(Long id) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE id = ?", new Object[]{id}, (rs, rowNum) -> rs.getLong("id")).stream().findFirst().isPresent();
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?", id);
    }

}
