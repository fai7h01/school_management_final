package com.school.service.impl;

import com.school.dto.LessonStudentDTO;
import com.school.entity.LessonStudent;
import com.school.repository.LessonStudentRepository;
import com.school.service.LessonStudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LessonStudentServiceImpl implements LessonStudentService {

    private final LessonStudentRepository lessonStudentRepository;
    private final MapperUtil mapper;

    public LessonStudentServiceImpl(LessonStudentRepository lessonStudentRepository, MapperUtil mapper) {
        this.lessonStudentRepository = lessonStudentRepository;
        this.mapper = mapper;
    }

    @Override
    public LessonStudentDTO save(LessonStudentDTO lessonStudentDTO) {
        LessonStudent saved = lessonStudentRepository.save(mapper.convert(lessonStudentDTO, new LessonStudent()));
        return mapper.convert(saved, new LessonStudentDTO());
    }

    @Override
    public List<LessonStudentDTO> findAllByLessonId(Long id) {
        return lessonStudentRepository.findAllByLessonId(id).stream()
                .map(lessonStudent -> mapper.convert(lessonStudent, new LessonStudentDTO())).toList();
    }

    @Override
    public List<LessonStudentDTO> findAllByStudentId(Long id) {
        return lessonStudentRepository.findAllByStudentId(id).stream()
                .map(lessonStudent -> mapper.convert(lessonStudent, new LessonStudentDTO()))
                .toList();
    }

    @Override
    public void delete(Long id) {
        LessonStudent found = lessonStudentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student/lesson not found."));
        found.setIsDeleted(true);
        lessonStudentRepository.save(found);
    }
}
