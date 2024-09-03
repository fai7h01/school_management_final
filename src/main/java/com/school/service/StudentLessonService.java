package com.school.service;

import com.school.dto.StudentLessonDto;

import java.util.List;

public interface StudentLessonService {

    StudentLessonDto save(StudentLessonDto studentLessonDto);
    List<StudentLessonDto> findAllByLessonId(Long id);

}
