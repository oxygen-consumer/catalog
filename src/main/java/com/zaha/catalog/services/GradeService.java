package com.zaha.catalog.services;

import com.zaha.catalog.domain.dto.GradeDto;
import com.zaha.catalog.domain.entities.GradeEntity;
import com.zaha.catalog.mappers.Mapper;
import com.zaha.catalog.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final Mapper<GradeEntity, GradeDto> gradeMapper;

    public GradeService(GradeRepository gradeRepository, Mapper<GradeEntity, GradeDto> gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    /*
    CREATE
     */
    public GradeDto createGrade(GradeDto gradeDto) {
        GradeEntity gradeEntity = gradeMapper.mapFrom(gradeDto);
        return gradeMapper.mapTo(gradeRepository.save(gradeEntity));
    }

    /*
    READ
     */
    public List<GradeDto> getAllGrades() {
        return StreamSupport.stream(gradeRepository
                                .findAll()
                                .spliterator(),
                        true
                )
                .map(gradeMapper::mapTo)
                .toList();
    }

    public Optional<GradeDto> getGradeById(Long id) {
        return gradeRepository.findById(id).map(gradeMapper::mapTo);
    }

    public boolean exists(Long id) {
        return gradeRepository.existsById(id);
    }

    /*
    UPDATE
     */
    public GradeDto updateGrade(GradeDto gradeDto) {
        GradeEntity gradeEntity = gradeMapper.mapFrom(gradeDto);
        return gradeMapper.mapTo(gradeRepository.save(gradeEntity));
    }

    public GradeDto patchGrade(GradeDto gradeDto) {
        GradeEntity gradeEntity = gradeRepository.findById(gradeDto.getId()).orElseThrow();
        gradeEntity.setGradeValue(gradeDto.getGradeValue());
        // TODO: update teacher and student
        return gradeMapper.mapTo(gradeRepository.save(gradeEntity));
    }

    /*
    DELETE
     */
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

}
