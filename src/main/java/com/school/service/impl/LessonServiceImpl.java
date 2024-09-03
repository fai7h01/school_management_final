package com.school.service.impl;

import com.school.dto.LessonDTO;
import com.school.entity.Lesson;
import com.school.repository.LessonRepository;
import com.school.service.LessonService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final MapperUtil mapper;

    public LessonServiceImpl(LessonRepository lessonRepository, MapperUtil mapper) {
        this.lessonRepository = lessonRepository;
        this.mapper = mapper;
    }

    @Override
    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> mapper.convert(lesson, new LessonDTO())).toList();
    }

    @Override
    public LessonDTO findById(Long id) {
        return null;
    }

    @Override
    public LessonDTO save(LessonDTO lesson) {
        return mapper.convert(lessonRepository.save(mapper.convert(lesson, new Lesson())), new LessonDTO());
    }

    @Override
    public LessonDTO update(LessonDTO lesson) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<LessonDTO> findAllByCourseId(Long id) {
        return lessonRepository.findAllByCourseId(id).stream()
                .map(lesson -> mapper.convert(lesson, new LessonDTO())).toList();
    }
}
