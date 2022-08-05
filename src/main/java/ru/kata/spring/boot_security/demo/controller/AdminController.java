package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/admin/user")
    public String getAdminUser(Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        return "user-admin";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(Model model, Authentication authentication) {
        model.addAttribute("user", userService.findByUsername(authentication.getName()));
        model.addAttribute("users", userService.findAllUser());
        return "users";
    }


    @GetMapping(value = "/admin/add")
    public String getUserCreateForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.findAllRole());
        return "new";
    }

    @PostMapping(value = "/admin/add")
    public String createNewUser(@RequestParam("role") ArrayList<Long> roles,
                                @ModelAttribute("user") User user) {
        user.setRoles(roleService.findByIdRoles(roles));
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String createUserFormEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.findAllRole());
        return "edit";
    }

    @PatchMapping(value = "/admin/edit")
    public String saveUpdateUser(@RequestParam("role") ArrayList<Long> roles,
                                 @ModelAttribute("user") User user) {
        user.setRoles(roleService.findByIdRoles(roles));
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String createLoginPage() {
        return "login";
    }
}
