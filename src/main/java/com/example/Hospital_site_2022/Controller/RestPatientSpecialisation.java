package com.example.Hospital_site_2022.Controller;


import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@AllArgsConstructor
@EnableAutoConfiguration
@RequestMapping("/api/specialisation")
public class RestPatientSpecialisation {

    private final SpecialisationService specialisationService;

    @PostMapping("/create")
    public ResponseEntity<Specialisation> createSpecialisation(@RequestBody Specialisation specialisation) {
        return specialisationService.createSpecialisation(specialisation);
    }

    @PostMapping("/update")
    public ResponseEntity<Specialisation> updateSpecialisation(@RequestBody Specialisation specialisation) {
        return specialisationService.updateSpecialisation(specialisation);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteSpecialisation(@RequestParam(value = "id") Long id){
        return specialisationService.deleteSpecialisation(id);
    }

    @PostMapping("/getSpecialisation")
    public ResponseEntity<Specialisation> getSpecialisation(@RequestParam(value = "id") Long id){
        return  specialisationService.getSpecialisation(id);
    }

    /**
     * method allow get a sorted list of patient
     * @param sortMethod asc; desc
     * @param title field's name
     * @return
     */
    @GetMapping("/allSpecialisation")
    public ResponseEntity<List<Specialisation>> getAllSpecialisations(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return specialisationService.getAllSpecialisation(sortMethod, title);
    }


}
