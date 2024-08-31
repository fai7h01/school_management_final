package com.school.service;

import com.school.dto.LessonDTO;

import java.util.List;

public interface LessonService {

    List<LessonDTO> findAll();

    LessonDTO findById(Long id);

    LessonDTO save(LessonDTO lesson);

    LessonDTO update(LessonDTO lesson);

    void delete(Long id);

    List<LessonDTO> findAllByCourseId(Long id);


}
