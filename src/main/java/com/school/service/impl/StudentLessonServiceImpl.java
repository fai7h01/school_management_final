package com.school.service.impl;

import com.school.dto.StudentLessonDto;
import com.school.entity.StudentLesson;
import com.school.repository.StudentLessonRepository;
import com.school.service.StudentLessonService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;
    private final MapperUtil mapper;

    public StudentLessonServiceImpl(StudentLessonRepository studentLessonRepository, MapperUtil mapper) {
        this.studentLessonRepository = studentLessonRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentLessonDto save(StudentLessonDto studentLessonDto) {
        StudentLesson saved = studentLessonRepository.save(mapper.convert(studentLessonDto, new StudentLesson()));
        return mapper.convert(saved, new StudentLessonDto());
    }

    @Override
    public List<StudentLessonDto> findAllByLessonId(Long id) {
        return studentLessonRepository.findAllByLessonId(id).stream()
                .map(studentLesson -> mapper.convert(studentLesson, new StudentLessonDto())).toList();
    }
}
