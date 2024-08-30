package com.school.service.impl;

import com.school.dto.CourseDTO;
import com.school.repository.CourseRepository;
import com.school.service.CourseService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MapperUtil mapper;

    public CourseServiceImpl(CourseRepository courseRepository, MapperUtil mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CourseDTO> listAllCourses() {
        return List.of();
    }

    @Override
    public CourseDTO save(CourseDTO course) {
        return null;
    }

    @Override
    public CourseDTO update(CourseDTO course) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
