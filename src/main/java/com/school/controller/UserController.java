package com.school.controller;

import com.school.dto.UserDTO;
import com.school.enums.State;
import com.school.service.RoleService;
import com.school.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

//
//    @GetMapping("/delete/{username}")
//    public String deleteUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
//        String eligibleToDelete = userService.isEligibleToDelete(username);
//        if (!eligibleToDelete.isEmpty()) {
//            redirectAttributes.addFlashAttribute("error", eligibleToDelete);
//        } else {
//
//            redirectAttributes.addFlashAttribute("success", "Successfully deleted");
//            userService.deleteById(username);
//        }
//
//
//        return "redirect:/user/create";
//    }
//
//    @GetMapping("/update/{username}")
//    public String updateUser(@PathVariable("username") String username, Model model) {
//
//        model.addAttribute("user", userService.findById(username));
//
//        model.addAttribute("roles", roleService.findAll());
//
//        model.addAttribute("states", State.values());
//
//        return "/user/user-update";
//    }
//
//    @PostMapping("/update")
//    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//
//
//        if (!userService.isEligibleToUpdate(user.getUserName(), user.getRole().getId())) {
//            redirectAttributes.addFlashAttribute("error", "Not allowed to update role");
//            return "redirect:/user/update/" + user.getUserName();
//        }
//
//
//        if (!userService.isPasswordMatched(user.getPassword(), user.getConfirmPassword())) {
//            bindingResult.rejectValue("confirmPassword", " ", "Password should match");
//        }
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("roles", roleService.findAll());
//
//            model.addAttribute("states", State.values());
//
//            return "/user/user-update";
//        }
//
//        redirectAttributes.addFlashAttribute("success", "Successfully updated");
//
//        userService.update(user);
//
//        return "redirect:/user/create";
//
//    }

    @ModelAttribute
    public void defineGeneralModels(Model model) {
        model.addAttribute("pageTitle", "User || Events");
    }
}
