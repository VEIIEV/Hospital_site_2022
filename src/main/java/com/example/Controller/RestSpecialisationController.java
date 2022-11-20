package com.example.Controller;


import com.example.DTO.SpecialisationDTO;
import com.example.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@EnableAutoConfiguration
@RequestMapping("/api/specialisation")
public class RestSpecialisationController {

    private final SpecialisationService specialisationService;

    @PostMapping("/create")
    public ResponseEntity<SpecialisationDTO> createSpecialisation(@RequestBody SpecialisationDTO specialisationDTO) {
        return specialisationService.createSpecialisation(specialisationDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<SpecialisationDTO> updateSpecialisation(@RequestBody SpecialisationDTO specialisationDTO) {
        return specialisationService.updateSpecialisation(specialisationDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteSpecialisation(@RequestParam(value = "id") Long id){
        return specialisationService.deleteSpecialisation(id);
    }

    @PostMapping("/getSpecialisation")
    public ResponseEntity<SpecialisationDTO> getSpecialisation(@RequestParam(value = "id") Long id){
        return  specialisationService.getSpecialisation(id);
    }

    /**
     * method allow get a sorted list of patient
     * @param sortMethod asc; desc
     * @param title field's name
     * @return
     */
    @GetMapping("/allSpecialisation")
    public ResponseEntity<List<SpecialisationDTO>> getAllSpecialisations(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return specialisationService.getAllSpecialisation(sortMethod, title);
    }


}
