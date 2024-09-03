package com.school.repository;

import com.school.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    List<CourseStudent> findAllByCourseIdAndIsEnrolled(Long id, boolean enrolled);

}
