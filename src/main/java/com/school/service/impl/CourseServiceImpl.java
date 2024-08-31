package com.school.service.impl;

import com.school.dto.CourseDTO;
import com.school.entity.Course;
import com.school.repository.CourseRepository;
import com.school.service.CourseService;
import com.school.service.LessonService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MapperUtil mapper;
    private final LessonService lessonService;

    public CourseServiceImpl(CourseRepository courseRepository, MapperUtil mapper, LessonService lessonService) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.lessonService = lessonService;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(course -> mapper.convert(course, new CourseDTO())).toList();
    }

    @Override
    public CourseDTO findById(Long id) {
        Course foundCourse = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course not find."));
        return mapper.convert(foundCourse, new CourseDTO());
    }

    @Override
    public CourseDTO save(CourseDTO course) {
        Course saved = courseRepository.save(mapper.convert(course, new Course()));
        return mapper.convert(saved, new CourseDTO());
    }

    @Override
    public CourseDTO update(CourseDTO course) {
        Course saved = courseRepository.save(mapper.convert(course, new Course()));
        return mapper.convert(saved, new CourseDTO());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean hasAssignedLessons(Long id) {
        return false;
    }
}
