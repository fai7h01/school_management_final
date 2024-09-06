package com.school.service.impl;

import com.school.dto.LessonStudentDTO;
import com.school.entity.LessonStudent;
import com.school.repository.StudentLessonRepository;
import com.school.service.LessonStudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LessonStudentServiceImpl implements LessonStudentService {

    private final StudentLessonRepository studentLessonRepository;
    private final MapperUtil mapper;

    public LessonStudentServiceImpl(StudentLessonRepository studentLessonRepository, MapperUtil mapper) {
        this.studentLessonRepository = studentLessonRepository;
        this.mapper = mapper;
    }

    @Override
    public LessonStudentDTO save(LessonStudentDTO lessonStudentDTO) {
        LessonStudent saved = studentLessonRepository.save(mapper.convert(lessonStudentDTO, new LessonStudent()));
        return mapper.convert(saved, new LessonStudentDTO());
    }

    @Override
    public List<LessonStudentDTO> findAllByLessonId(Long id) {
        return studentLessonRepository.findAllByLessonId(id).stream()
                .map(lessonStudent -> mapper.convert(lessonStudent, new LessonStudentDTO())).toList();
    }

    @Override
    public void delete(Long id) {
        LessonStudent found = studentLessonRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student/lesson not found."));
        found.setIsDeleted(true);
        studentLessonRepository.save(found);
    }
}
