package com.school.repository;

import com.school.entity.LessonStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLessonRepository extends JpaRepository<LessonStudent, Long> {

    List<LessonStudent> findAllByLessonId(Long id);

}
