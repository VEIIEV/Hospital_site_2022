package com.example.controller.rest;

import com.example.DTO.DoctorDTO;
import com.example.Entity.Doctor;
import com.example.Services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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



    @GetMapping("/doctorBySpecialisation")
    public ResponseEntity<Set<String>> getDoctorBySpecialisation(@RequestParam(value = "specialisation") String specialisation){
        return doctorService.getDoctorBySpecialisation(specialisation);
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
