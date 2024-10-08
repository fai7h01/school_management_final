package com.school.controller;

import com.school.dto.LessonDTO;
import com.school.service.CourseService;
import com.school.service.LessonService;
import com.school.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public String insertLesson(@ModelAttribute("lesson") LessonDTO lessonDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("lessons", lessonService.findAll());
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.listAllByRole("instructor"));
            return "/lesson/lesson-create";
        }
        lessonService.save(lessonDTO);
        return "redirect:/lesson/create";
    }

    @GetMapping("/update/{id}")
    public String editLesson(@PathVariable Long id, Model model){
        model.addAttribute("lesson", lessonService.findById(id));
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("instructors", userService.listAllByRole("instructor"));
        return "/lesson/lesson-update";
    }

    @PostMapping("/update/{id}")
    public String updateLesson(@ModelAttribute("lesson") LessonDTO lessonDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.listAllByRole("instructor"));
            return "/lesson/lesson-update";
        }
        lessonService.update(lessonDTO);
        return "redirect:/lesson/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id){
        lessonService.delete(id);
        return "redirect:/lesson/create";
    }
}
