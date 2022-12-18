package com.example.controller.rest;

import com.example.DTO.HospitalDTO;
import com.example.Entity.Diagnosis;
import com.example.Services.DiagnosisService;
import com.example.Services.HospitalService;
import com.example.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {

    //свалка контроллеров, потом удалить и раскидать
    HospitalService hospitalService;
    SpecialisationService specialisationService;
    private DiagnosisService diagnosisService;


    @PostMapping("/hospital/create")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO){
        return hospitalService.createHospital(hospitalDTO);
    }

    @PostMapping("/disease")
    public ResponseEntity<Diagnosis> createDisease(@RequestBody Diagnosis diagnosis){
        return diagnosisService.createDisease(diagnosis);
    }

}
