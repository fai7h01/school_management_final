package com.school.repository;

import com.school.entity.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Long> {

    List<StudentLesson> findAllByLessonId(Long id);

}
