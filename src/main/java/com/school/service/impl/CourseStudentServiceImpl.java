package com.school.service.impl;

import com.school.dto.CourseStudentDTO;
import com.school.entity.CourseStudent;
import com.school.repository.CourseStudentRepository;
import com.school.service.CourseStudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public List<CourseStudentDTO> findAllByStudentIdAndIsEnrolled(Long studentId, boolean enrolled) {
        return courseStudentRepository.findAllByStudentIdAndIsEnrolled(studentId, enrolled).stream()
                .map(courseStudent -> mapper.convert(courseStudent, new CourseStudentDTO())).toList();
    }

    @Override
    public void delete(Long id) {
        CourseStudent found = courseStudentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CourseStudent not found."));
        found.setIsDeleted(true);
        courseStudentRepository.save(found);
    }
}
