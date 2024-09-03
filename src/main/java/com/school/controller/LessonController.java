package com.school.controller;

import com.school.dto.LessonDTO;
import com.school.service.CourseService;
import com.school.service.LessonService;
import com.school.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserService userService;

    public LessonController(LessonService lessonService, CourseService courseService, UserService userService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createLesson(Model model){
        model.addAttribute("lesson", new LessonDTO());
        model.addAttribute("lessons", lessonService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("instructors", userService.listAllByRole("instructor"));
        return "/lesson/lesson-create";
    }
}
