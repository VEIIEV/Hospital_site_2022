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

    //свалка контроллеров, потом удалить и раскидать
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

    @PostMapping("/hospital/create")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO){
        return hospitalService.createHospital(hospitalDTO);
    }


}
