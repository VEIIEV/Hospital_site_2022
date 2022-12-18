package com.example.controller;


import com.example.Entity.User;
import com.example.Services.EmailService;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RegistrationController {


    private static final String REDIRECT_LOGIN= "redirect:/login";

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    EmailService emailService;

    @Autowired
    private MessageSource messageSource;

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
        return "redirect:/mailInfoPage";
    }

    @GetMapping("/mailInfoPage")
    public String showMailVerificationNotion() {
        return "mailInfoPage";
    }


    //A specialization of the Model interface that controllers can use to select attributes for a redirect scenario.
    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirAttr){
        if(StringUtils.isEmpty(token)){
            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }
        try {
            userDetailsService.verifyUser(token);
        } catch (Exception e) {
            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }

        redirAttr.addAttribute("verifiedAccountMsg", "user.registration.verification.success");
        return REDIRECT_LOGIN;
    }

}
