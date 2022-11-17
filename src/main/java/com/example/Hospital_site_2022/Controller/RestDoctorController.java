package com.example.Hospital_site_2022.Controller;

import com.example.Hospital_site_2022.DTO.DoctorDTO;
import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@AllArgsConstructor
@RequestMapping("/api/doctor")
public class RestDoctorController {

    private final DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.createDoctor(doctorDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(doctorDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteDoctor(@RequestParam(value = "id") Long id){
        return doctorService.deleteDoctor(id);
    }

    @PostMapping("/getDoctor")
    public ResponseEntity<Doctor> getDoctor(@RequestParam(value = "id") Long id){
        return  doctorService.getDoctor(id);
    }

    /**
     * method allow get a sorted list of patient
     * @param sortMethod asc; desc
     * @param title field's name
     * @return
     */
    @GetMapping("/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return doctorService.getAllDoctor(sortMethod, title);
    }
}
