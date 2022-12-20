package com.example.controller;


import com.example.DTO.PatientCardDTO;
import com.example.Entity.Diagnosis;
import com.example.Entity.PatientCard;
import com.example.Entity.ReceptionHour;
import com.example.Repository.DiagnosisRepository;
import com.example.Services.DiagnosisService;
import com.example.Services.PatientCardService;
import com.example.Services.ReceptionHourService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;


    @Autowired
    PatientCardService patientCardService;
    @Autowired
    ReceptionHourService receptionHourService;
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

    @GetMapping("/diagnose/{id}")
    public  String diagnosisForm(@PathVariable Long id, Model model){
        ReceptionHour receptionHour = receptionHourService.getReceptionHourNoApi(id);
        model.addAttribute("receptionHour", receptionHour);
        return "diagnosisForm";

    }

    @PostMapping("/save")
    public String makeDiagnose(@ModelAttribute("patientCard")PatientCardDTO patientCardDTO){

        patientCardService.makeDiagnose(patientCardDTO);
        return "helloPage";
    }

}
