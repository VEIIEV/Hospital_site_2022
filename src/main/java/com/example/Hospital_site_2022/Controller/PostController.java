package com.example.Hospital_site_2022.Controller;

import com.example.Hospital_site_2022.DTO.HospitalDTO;
import com.example.Hospital_site_2022.Services.HospitalService;
import com.example.Hospital_site_2022.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
@RequestMapping("/")
@AllArgsConstructor
public class PostController {

    //свалка контроллеров, потом удалить и раскидать
    HospitalService hospitalService;
    SpecialisationService specialisationService;



    @PostMapping("/hospital/create")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO){
        return hospitalService.createHospital(hospitalDTO);
    }


}
