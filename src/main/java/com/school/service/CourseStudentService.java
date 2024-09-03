package com.school.service;

import com.school.dto.CourseStudentDTO;

import java.util.List;

public interface CourseStudentService {

    List<CourseStudentDTO> findAllByCourseIdAndIsEnrolled(Long courseId, boolean enrolled);

}
