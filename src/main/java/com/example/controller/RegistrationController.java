package com.example.controller;


import com.example.Entity.User;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/form")
    public String showRegistrationForm(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult) {
        return "registration";

    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userDetailsService.loadUserByUsername(userForm.getUserName()) != null) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        userDetailsService.saveUser(userForm);
        return "redirect:/hello";
    }


}
