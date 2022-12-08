package com.example.controller;


import com.example.DTO.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class RegistrationController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }



    @GetMapping("/form")
    public String showRegistrationForm(WebRequest webRequest, Model model){
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("user",patientDTO);
        return "registration";

    }




}
