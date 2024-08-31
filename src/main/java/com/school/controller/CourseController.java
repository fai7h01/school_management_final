package com.school.controller;

import com.school.dto.CourseDTO;
import com.school.entity.Course;
import com.school.service.CourseService;
import com.school.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createCourse(Model model){
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("managers", userService.listAllByRole("Manager"));
        return "/course/course-create";
    }

    @PostMapping("/create")
    public String insertCourse(@ModelAttribute("course") CourseDTO courseDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("managers", userService.listAllByRole("Manager"));
            return "/course/course-create";
        }
        CourseDTO saved = courseService.save(courseDTO);
        redirectAttributes.addFlashAttribute("success", saved.getName() + " is successfully added to courses");
        return "redirect:/course/create";
    }
}
