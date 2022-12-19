package com.example.controller;

import com.example.Entity.Doctor;
import com.example.Entity.ReceptionHour;
import com.example.Entity.Specialisation;
import com.example.Repository.DoctorRepository;
import com.example.Repository.SpecialisationRepository;
import com.example.Services.DoctorService;
import com.example.Services.ReceptionHourService;
import com.example.Services.SpecialisationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/specialisation")
public class SpecialisationController {

    @Autowired
    SpecialisationService specialisationService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ReceptionHourService receptionHourService;
    private final DoctorRepository doctorRepository;
    private final SpecialisationRepository specialisationRepository;

    @GetMapping("/")
    public String getSpecialisation(Model model){

        List<Specialisation> specialisations = specialisationService.getAllSpecialisation();
        model.addAttribute("specialisations", specialisations);
        return "specialisation";
    }

    @GetMapping("/{id}")
    public String getSpecificSpecialisation(@PathVariable("id") Long id, Model model){
        Specialisation specialisation =specialisationService.getSpecialisationNoApi(id);
        List<Doctor> doctors = doctorService.getDoctorBySpecialisationNoApi(specialisation);
        model.addAttribute("specialisation", specialisation);
        model.addAttribute("doctors", doctors);

        return "Doctors";

    }

    @GetMapping("/sort/{title}")
    public String getSortedSpecialisation(@PathVariable("title") String title, Model model){
        List<Specialisation> specialisations= specialisationService.getAllDiagnosisNoApi("asc", title);
        model.addAttribute("specialisations", specialisations);
        return "redirect:/specialisation/";
    }

    @GetMapping("/{id}/sort/{title}")
    public String getSortedDoctor(@PathVariable("title") String title,
                                  @PathVariable Long id,
                                  Model model){
        Specialisation specialisation =specialisationRepository.findById(id).get();
        List<Doctor> doctors = doctorService.getSortedDoctorBySpecialisationNoApi(
                title,
                specialisation);
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialisation", specialisation);
         return "Doctors";
    }

    @GetMapping("/doctor/{id}")
    public String getSpecificDoctor(@PathVariable("id") Long id, Model model){
        Doctor doctor = doctorService.getDoctorNoApi(id);
        List<ReceptionHour> receptionHours = receptionHourService.getAllReceptionHourNoApi(doctor);
        model.addAttribute("doctor", doctor);
        model.addAttribute("receptionHours", receptionHours);

        return "appointment";
    }

}