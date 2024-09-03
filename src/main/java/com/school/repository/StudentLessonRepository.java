package com.school.repository;

import com.school.entity.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Long> {
}
