package com.school.service.impl;

import com.school.dto.StudentDTO;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
