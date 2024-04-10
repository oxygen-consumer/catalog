package com.zaha.catalog.repositories;

import com.zaha.catalog.domain.entities.GradeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {
}
