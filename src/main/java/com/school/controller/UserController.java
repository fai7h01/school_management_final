package com.school.controller;

import com.school.dto.UserDTO;
import com.school.enums.State;
import com.school.service.RoleService;
import com.school.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("users", userService.listAllUsers());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        return "/user/user-create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (userService.isEmailRegistered(user.getUserName())) {
            redirectAttributes.addFlashAttribute("error", "This email address has been already registered.");
            return "redirect:/user/create";
        }

        if (!userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            model.addAttribute("users", userService.listAllUsers());
            return "/user/user-create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String eligibleToDelete = userService.isEligibleToDelete(id);
        if (!eligibleToDelete.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", eligibleToDelete);
        } else {
            redirectAttributes.addFlashAttribute("success", "Successfully deleted");
            userService.delete(id);
        }
        return "redirect:/user/create";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        return "/user/user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") UserDTO user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (!userService.isRoleEligibleToUpdate(user.getId(), user.getRole().getId())) {
            redirectAttributes.addFlashAttribute("error", "Not allowed to update role");
            return "redirect:/user/update/" + user.getId();
        }

        if (!userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", " ", "Password should match");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            return "/user/user-update";
        }

        redirectAttributes.addFlashAttribute("success", "Successfully updated");
        userService.update(user);
        return "redirect:/user/create";
    }

    @ModelAttribute
    public void defineGeneralModels(Model model) {
        model.addAttribute("pageTitle", "User || Events");
    }
}
