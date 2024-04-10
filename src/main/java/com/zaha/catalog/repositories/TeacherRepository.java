package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.TeacherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
}
