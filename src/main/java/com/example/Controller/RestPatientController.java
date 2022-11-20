package com.example.Controller;

import com.example.DTO.PatientDTO;
import com.example.DTO.PatientDTOWithId;
import com.example.Services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@AllArgsConstructor
@RequestMapping("/api/patient")
public class RestPatientController {

    private final PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTOWithId patientDTOWithId) {
        return patientService.updatePatient(patientDTOWithId);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deletePatient(@RequestParam(value = "id") Long id){
        return patientService.deletePatient(id);
    }

    @PostMapping("/getPatient")
    public ResponseEntity<PatientDTO> getPatient(@RequestParam(value = "id") Long id){
        return  patientService.getPatient(id);
    }

    /**
     * method allow get a sorted list of patient
     * @param sortMethod asc; desc
     * @param title field's name
     * @return
     */
    @GetMapping("/allPatient")
    public ResponseEntity<List<PatientDTO>> getAllPatients(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return patientService.getAllPatient(sortMethod, title);
    }


    //дописать
    @GetMapping("/filteredPatient")
    public ResponseEntity<List<PatientDTO>> getFilteredPatients(

    ) {

        return null;
    }


}