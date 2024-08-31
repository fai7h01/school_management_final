package com.school.service;

import com.school.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> findAll();

    CourseDTO findById(Long id);

    CourseDTO save(CourseDTO course);

    CourseDTO update(CourseDTO course);

    void delete(Long id);

}
