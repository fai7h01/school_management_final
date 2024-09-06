package com.school.service;

import com.school.dto.LessonStudentDTO;

import java.util.List;

public interface LessonStudentService {

    LessonStudentDTO save(LessonStudentDTO lessonStudentDTO);
    List<LessonStudentDTO> findAllByLessonId(Long id);
    void delete(Long id);
}
