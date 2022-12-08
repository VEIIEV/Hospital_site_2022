package com.example.controller;


import com.example.Entity.Hospital;
import com.example.Repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;


@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class MainController {


    @Autowired
    HospitalRepository hospitalRepository;


    @GetMapping("/hello")
    public String hello(Model model){

        Hospital hospital = hospitalRepository.findById(2L).orElseThrow(NoSuchElementException::new);

        model.addAttribute("hospital", hospital);
        return "helloPage";
    }
}
