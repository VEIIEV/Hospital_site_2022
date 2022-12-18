package com.example.controller;


import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import com.example.Entity.User;
import com.example.Repository.DoctorRepository;
import com.example.Repository.HospitalRepository;
import com.example.Repository.PatientRepository;
import com.example.Repository.UserRepository;
import com.example.Services.DoctorService;
import com.example.Services.PatientService;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.NoSuchElementException;


@AllArgsConstructor
@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class MainController {


    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PatientService patientservice;

    @Autowired
    DoctorService doctorService;


    @GetMapping("/hello")
    public String hello(Model model) {

        Hospital hospital = hospitalRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        model.addAttribute("hospital", hospital);
        return "helloPage";
    }

    @GetMapping("/")
    public String hello2(Model model) {

        Hospital hospital = hospitalRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        model.addAttribute("hospital", hospital);
        return "helloPage";
    }

    @GetMapping("/faq")
    public String faq() {
        return "FAQ";
    }

    @GetMapping("/patientCard")
    public String patientCard(Model model, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
      boolean check= user.getUserRole().name().equals("ROLE_PATIENT");
        if(check){
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));
        }
        else if(user.getUserRole().name().equals("ROLE_DOCTOR")){
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));
        }
        model.addAttribute("user", user);
        return "patientCard";
    }

    @PostMapping("/patientCard/updateImg")
    String uploadImage(@RequestParam("image") MultipartFile multipartImage, Principal principal, Model model) throws Exception {

        userDetailsService.updateImg(multipartImage, principal);
        User user = userRepository.findByUsername(principal.getName());
        if(user.getUserRole().equals("ROLE_PATIENT")){
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));
        }
        else if(user.getUserRole().equals("ROLE_DOCTOR")){
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));
        }
        model.addAttribute("user", user);
        System.out.println(user.getImage().length);
        return "redirect:/patientCard";
    }

    @PostMapping("/patientCard/updateData/patient")
    String uploadImage(@ModelAttribute("patient") Patient patient, Principal principal, Model model) throws Exception {

        patientservice.updatePatientData(patient, principal);
        User user = userRepository.findByUsername(principal.getName());
        if(user.getUserRole().equals("ROLE_PATIENT")){
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));
        }
        else if(user.getUserRole().equals("ROLE_DOCTOR")){
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));
        }
        model.addAttribute("user", user);
        return "redirect:/patientCard";
    }


    @PostMapping("/patientCard/updateData/doctor")
    String uploadImage(@ModelAttribute("doctor") Doctor doctor, Principal principal, Model model) throws Exception {

        doctorService.updateDoctor(doctor, principal);
        User user = userRepository.findByUsername(principal.getName());
        if(user.getUserRole().equals("ROLE_PATIENT")){
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));
        }
        else if(user.getUserRole().equals("ROLE_DOCTOR")){
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));
        }
        model.addAttribute("user", user);
        return "redirect:/patientCard";
    }

//    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
//    Resource downloadImage(@PathVariable Long imageId) {
//        byte[] image = imageRepository.findById(imageId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
//                .getContent();
//
//        return new ByteArrayResource(image);
//    }


}
