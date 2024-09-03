package com.school.service.impl;

import com.school.dto.CourseStudentDTO;
import com.school.repository.CourseStudentRepository;
import com.school.service.CourseStudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final MapperUtil mapper;

    public CourseStudentServiceImpl(CourseStudentRepository courseStudentRepository, MapperUtil mapper) {
        this.courseStudentRepository = courseStudentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CourseStudentDTO> findAllByCourseIdAndIsEnrolled(Long courseId, boolean enrolled) {
        return courseStudentRepository.findAllByCourseIdAndIsEnrolled(courseId, enrolled).stream()
                .map(courseStudent -> mapper.convert(courseStudent, new CourseStudentDTO())).toList();
    }
}
