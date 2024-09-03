package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.enums.State;
import com.school.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create")
    public String studentCreate(Model model){
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("states", State.values());
        return "/student/student-create";
    }
}
