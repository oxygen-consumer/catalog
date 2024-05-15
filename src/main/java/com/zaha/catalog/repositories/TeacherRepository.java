package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.TeacherEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    public TeacherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TeacherEntity save(TeacherEntity teacherEntity) {
        Long id = this.jdbcTemplate.queryForObject("SELECT nextval('teacher_id_seq')", Long.class);
        teacherEntity.setId(id);
        this.jdbcTemplate.update("INSERT INTO teachers (id, name, birth_date, email) VALUES (?, ?, ?, ?)",
                teacherEntity.getId(),
                teacherEntity.getName(),
                teacherEntity.getBirthDate(),
                teacherEntity.getEmail());
        return teacherEntity;
    }

    public Iterable<TeacherEntity> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM teachers", (rs, rowNum) -> TeacherEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .birthDate(rs.getDate("birth_date"))
                .email(rs.getString("email"))
                .build());
    }

    public Optional<TeacherEntity> findById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM teachers WHERE id = ?", new Object[]{id}, (rs, rowNum) -> Optional.of(TeacherEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .birthDate(rs.getDate("birth_date"))
                .email(rs.getString("email"))
                .build())).stream().findFirst().orElse(Optional.empty());
    }

    public boolean existsById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM teachers WHERE id = ?", new Object[]{id}, (rs, rowNum) -> rs.getLong("id")).stream().findFirst().isPresent();
    }

    public void deleteById(Long id) {
        this.jdbcTemplate.update("DELETE FROM teachers WHERE id = ?", id);
    }

}
