package com.school.service;

import com.school.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll();

    StudentDTO findById(Long id);

    StudentDTO save(StudentDTO student);

    StudentDTO update(StudentDTO student);

    void delete(Long id);

}
