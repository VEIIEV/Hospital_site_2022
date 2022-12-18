package com.example.controller;


import com.example.Entity.Diagnosis;
import com.example.Services.DiagnosisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping("/")
    public String getDiagnosis(Model model){

        List<Diagnosis> diagnoses = diagnosisService.getAllDiagnosis();
        model.addAttribute("diagnoses", diagnoses);
        return "diagnosis";
    }

}
