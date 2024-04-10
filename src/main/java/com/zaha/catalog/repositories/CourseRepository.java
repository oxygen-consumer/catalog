package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
}
