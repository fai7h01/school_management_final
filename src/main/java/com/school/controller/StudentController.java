package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.enums.State;
import com.school.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create")
    public String createStudent(Model model){
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("states", State.values());
        return "/student/student-create";
    }

    @PostMapping("/create")
    public String insertStudent(@ModelAttribute("student") StudentDTO studentDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("students", studentService.findAll());
            model.addAttribute("states", State.values());
            return "/student/student-create";
        }
        studentService.save(studentDTO);
        return "redirect:/student/create";
    }

    @GetMapping("/update/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("states", State.values());
        return "/student/student-update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("states", State.values());
        }
        studentService.update(studentDTO);
        return "redirect:/student/create";
    }
}
