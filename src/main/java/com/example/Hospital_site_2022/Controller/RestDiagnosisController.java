package com.example.Hospital_site_2022.Controller;


import com.example.Hospital_site_2022.DTO.DiagnosisDTO;
import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Services.DiagnosisService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@AllArgsConstructor
@RequestMapping("/api/diagnosis")
public class RestDiagnosisController {

    DiagnosisService diagnosisService;

    @PostMapping("/create")
    public ResponseEntity<DiagnosisDTO> create(@RequestBody DiagnosisDTO diagnosisDTO){
        return diagnosisService.createDiagnosis(diagnosisDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<DiagnosisDTO> update(@RequestBody DiagnosisDTO diagnosisDTO){
        return diagnosisService.updateDiagnosis(diagnosisDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteDiagnosis(@RequestParam(value = "id") Long id){
        return diagnosisService.deleteDiagnosis(id);
    }

    @PostMapping("/getDiagnosis")
    public ResponseEntity<DiagnosisDTO> getDiagnosis(@RequestParam(value = "id") Long id){
        return  diagnosisService.getDiagnosis(id);
    }

    @GetMapping("/allDiagnosis")
    public ResponseEntity<List<DiagnosisDTO>> getAllPatients(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return diagnosisService.getAllDiagnosis(sortMethod, title);
    }

    //создать метод, который выводит количество пациентов с определенным диагнозом

}
