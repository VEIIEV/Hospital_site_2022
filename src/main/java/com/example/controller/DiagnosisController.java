package com.example.controller;


import com.example.Entity.Diagnosis;
import com.example.Repository.DiagnosisRepository;
import com.example.Services.DiagnosisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;
    private final DiagnosisRepository diagnosisRepository;

    @GetMapping("/")
    public String getDiagnosis(Model model){

        List<Diagnosis> diagnoses = diagnosisService.getAllDiagnosis();
        model.addAttribute("diagnoses", diagnoses);
        return "diagnosis";
    }

    @GetMapping("/{id}")
    public String getSpecificDiagnosis(@PathVariable("id") Long id, Model model){
        Diagnosis diagnosis =diagnosisService.getDiagnosisById(id);
        model.addAttribute("diagnosis", diagnosis);
        return "diagnosisPage";

    }

    @GetMapping("/sort/{title}")
    public String getSortedDiagnosis(@PathVariable("title") String title, Model model){
        List<Diagnosis> diagnosis= diagnosisService.getAllDiagnosis("asc", title);
        model.addAttribute("diagnoses", diagnosis);
        return "diagnosis";
    }

}
