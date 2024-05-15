package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.StudentEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudentEntity save(StudentEntity studentEntity) {
        Long id = this.jdbcTemplate.queryForObject("SELECT nextval('student_id_seq')", Long.class);
        studentEntity.setId(id);
        this.jdbcTemplate.update("INSERT INTO students (id, name, birth_date, email) VALUES (?, ?, ?, ?)",
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getBirthDate(),
                studentEntity.getEmail());
        return studentEntity;
    }

    public Iterable<StudentEntity> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM students", (rs, rowNum) -> StudentEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .birthDate(rs.getDate("birth_date"))
                .email(rs.getString("email"))
                .build());
    }

    public Optional<StudentEntity> findById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM students WHERE id = ?", new Object[]{id}, (rs, rowNum) -> Optional.of(StudentEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .birthDate(rs.getDate("birth_date"))
                .email(rs.getString("email"))
                .build())).stream().findFirst().orElse(Optional.empty());
    }

    public boolean existsById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM students WHERE id = ?", new Object[]{id}, (rs, rowNum) -> rs.getLong("id")).stream().findFirst().isPresent();
    }

    public void deleteById(Long id) {
        this.jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

}
