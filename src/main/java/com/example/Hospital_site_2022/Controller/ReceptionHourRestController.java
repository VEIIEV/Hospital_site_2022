package com.example.Hospital_site_2022.Controller;

import com.example.Hospital_site_2022.DTO.ReceptionHourDTO;
import com.example.Hospital_site_2022.Services.ReceptionHourService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/receptionHour")
@AllArgsConstructor
public class ReceptionHourRestController {

    private  final ReceptionHourService receptionHourService;


    @GetMapping("/test") ResponseEntity<List<ReceptionHourDTO>> test(){
        return receptionHourService.findOutdated();
    }


    @PostMapping("/makeAppointment") ResponseEntity<ReceptionHourDTO> makeAppointment(@RequestBody ReceptionHourDTO receptionHourDTO){
        return  receptionHourService.makeAppointment(receptionHourDTO);
    }


    @PostMapping("/create")
    public ResponseEntity<ReceptionHourDTO> create(@RequestBody ReceptionHourDTO receptionHourDTO){
        return receptionHourService.createReceptionHour(receptionHourDTO);
    }

    @PostMapping("/createForMonth")
    public ResponseEntity<Integer> createForMonth(@RequestParam("doctor_id") Long doctor_id){
        return receptionHourService.createMonthScheduleFor(doctor_id);
    }

    @PostMapping("/update")
    public ResponseEntity<ReceptionHourDTO> update(@RequestBody ReceptionHourDTO receptionHourDTO){
        return receptionHourService.updateReceptionHour(receptionHourDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteReceptionHour(@RequestParam(value = "id") Long id){
        return receptionHourService.deleteReceptionHour(id);
    }

    @PostMapping("/getDiagnosis")
    public ResponseEntity<ReceptionHourDTO> getReceptionHour(@RequestParam(value = "id") Long id){
        return  receptionHourService.getReceptionHour(id);
    }

    @GetMapping("/allDiagnosis")
    public ResponseEntity<List<ReceptionHourDTO>> getAllReceptionHours(
            @RequestParam(value = "sortMethod", required = false) String sortMethod,
            @RequestParam(value = "title", required = false) String title) {
        return receptionHourService.getAllReceptionHour(sortMethod, title);
    }
}
