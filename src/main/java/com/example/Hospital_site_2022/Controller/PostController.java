package com.example.Hospital_site_2022.Controller;

import com.example.Hospital_site_2022.DTO.HospitalDTO;
import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Services.DataService;
import com.example.Hospital_site_2022.Services.HospitalService;
import com.example.Hospital_site_2022.Services.PatientService;
import com.example.Hospital_site_2022.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@EnableAutoConfiguration
@RequestMapping("/")
@AllArgsConstructor
public class PostController {

    
    DataService dataService;
    PatientService patientService;
    HospitalService hospitalService;


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

    //// controller
    //@ResponseBody @RequestMapping("/description")
    //public Description getDescription(@RequestBody UserStats stats){
    //    return new Description(stats.getFirstName() + " " + stats.getLastname() + " hates wacky wabbits");
    //}

    @PostMapping("/patient/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTOWithId patientDTOWithId){
        return patientService.updatePatient(patientDTOWithId);
    }

    @PostMapping("/patient/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }


    @PostMapping("/hospital/create")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO){

        return hospitalService.createHospital(hospitalDTO);


    }
}
