package com.school.service.impl;

import com.school.dto.StudentDTO;
import com.school.entity.Student;
import com.school.repository.StudentRepository;
import com.school.service.CourseStudentService;
import com.school.service.LessonStudentService;
import com.school.service.StudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final MapperUtil mapper;
    private final CourseStudentService courseStudentService;
    private final LessonStudentService lessonStudentService;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapper, CourseStudentService courseStudentService, LessonStudentService lessonStudentService) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.courseStudentService = courseStudentService;
        this.lessonStudentService = lessonStudentService;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(student -> mapper.convert(student, new StudentDTO())).toList();
    }

    @Override
    public StudentDTO findById(Long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found."));
        return mapper.convert(foundStudent, new StudentDTO());
    }

    @Override
    public StudentDTO save(StudentDTO student) {
        Student saved = studentRepository.save(mapper.convert(student, new Student()));
        return mapper.convert(saved, new StudentDTO());
    }

    @Override
    public StudentDTO update(StudentDTO student) {
        return mapper.convert(studentRepository.save(mapper.convert(student, new Student())), new StudentDTO());
    }

    @Override
    public void delete(Long id) {
        /* 1. check if student is enrolled in any course
                find courseStudent by student id and delete all of them
           2. check if enrolled student has any lessons or assessments
                find studentLessons by student id and delete all of them
         */
    }
}
