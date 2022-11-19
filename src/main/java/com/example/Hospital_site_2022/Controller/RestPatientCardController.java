package com.example.Hospital_site_2022.Controller;


import com.example.Hospital_site_2022.DTO.PatientCardDTO;
import com.example.Hospital_site_2022.Services.PatientCardService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@EnableAutoConfiguration
@AllArgsConstructor
@RequestMapping("/api/patientCard")
public class RestPatientCardController {

    PatientCardService patientCardService;

    @PostMapping("/create")
    public ResponseEntity<PatientCardDTO> create(@RequestBody PatientCardDTO patientCardDTO){
        return patientCardService.createPatientCard(patientCardDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<PatientCardDTO> update(@RequestBody PatientCardDTO patientCardDTO){
        return patientCardService.updatePatientCard(patientCardDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deletePatientCard(@RequestParam(value = "id") Long id){
        return patientCardService.deletePatientCard(id);
    }

    @PostMapping("/getPatientCard")
    public ResponseEntity<PatientCardDTO> getPatientCard(@RequestParam(value = "id") Long id){
        return  patientCardService.getPatientCard(id);
    }

    @GetMapping("/allPatientCard")
    public ResponseEntity<List<PatientCardDTO>> getAllPatientCards(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return patientCardService.getAllPatientCard(sortMethod, title);
    }



    @GetMapping("/visitByDoctor")
    public ResponseEntity<Set<PatientCardDTO>> getVisitByDoctor(@RequestParam(value = "doctorId") Long dockorId){
        return  patientCardService.getPatientCardByDoctorId(dockorId);
    }

    @GetMapping("/visitByPatient")
    public ResponseEntity<Set<PatientCardDTO>> getVisitByPatient(@RequestParam(value = "patientId") Long patientId){
        return  patientCardService.getPatientCardByPatientId(patientId);
    }





    //создать метод, который выводит количество пациентов с определенным диагнозом

}
