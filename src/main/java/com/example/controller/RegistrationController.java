package com.example.controller;


import com.example.DTO.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/registration")
public class RegistrationController {


    @GetMapping("/form")
    public String showRegistrationForm(WebRequest webRequest, Model model){
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("user",patientDTO);
        return "registration";

    }




}
