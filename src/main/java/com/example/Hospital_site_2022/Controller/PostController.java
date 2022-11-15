package com.example.Hospital_site_2022.Controller;

import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Services.DataService;
import com.example.Hospital_site_2022.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
@RequestMapping("/")
@AllArgsConstructor
public class PostController {

    
    DataService dataService;

    SpecialisationService specialisationService;

    @GetMapping("/diagnosis")
    public String giveMyDiagnosis(@RequestBody Diagnosis diagnosis){


        return diagnosis.toString();
    }

    @PostMapping("/saveSpecialisation")
    public String saveSpecialisation(@RequestBody Specialisation specialisation){
        specialisationService.saveSpecialisation(specialisation);
        return "";
    }
}
