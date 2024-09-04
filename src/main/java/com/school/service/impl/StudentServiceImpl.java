package com.school.service.impl;

import com.school.dto.StudentDTO;
import com.school.entity.Student;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final MapperUtil mapper;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
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

    }
}
