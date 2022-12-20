package com.example.controller;


import com.example.Entity.*;
import com.example.Repository.*;
import com.example.Services.DoctorService;
import com.example.Services.PatientService;
import com.example.Services.ReceptionHourService;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
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

    @Autowired
    ReceptionHourService receptionHourService;
    private final ReceptionHourRepository receptionHourRepository;
    private final PatientCardRepository patientCardRepository;


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

    @GetMapping("/account")
    public String account(Model model, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());
        List<ReceptionHour> receptionHourForAccount;

        if (user.getUserRole().name().equals("ROLE_PATIENT")) {
            receptionHourForAccount = receptionHourService.getPatientReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));


        } else if (user.getUserRole().name().equals("ROLE_DOCTOR")) {
            receptionHourForAccount = receptionHourService.getDoctorReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));

        }

        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/account/updateImg")
    String uploadImage(@RequestParam("image") MultipartFile multipartImage, Principal principal, Model model) throws Exception {

        userDetailsService.updateImg(multipartImage, principal);

        User user = userRepository.findByUsername(principal.getName());
        if (user.getUserRole().equals("ROLE_PATIENT")) {
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));
        } else if (user.getUserRole().equals("ROLE_DOCTOR")) {
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));
        }
        model.addAttribute("user", user);
        System.out.println(user.getImage().length);
        return "redirect:/account";
    }

    @PostMapping("/account/updateData/patient")
    String updateDataPatient(@ModelAttribute("patient") Patient patient, Principal principal, Model model) throws Exception {
        //
        patientservice.updatePatientData(patient, principal);
        //
        User user = userRepository.findByUsername(principal.getName());
        List<ReceptionHour> receptionHourForAccount;

        if (user.getUserRole().name().equals("ROLE_PATIENT")) {
            receptionHourForAccount = receptionHourService.getPatientReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));

        } else if (user.getUserRole().name().equals("ROLE_DOCTOR")) {
            receptionHourForAccount = receptionHourService.getDoctorReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));

        }
        model.addAttribute("user", user);
        return "redirect:/account";
    }


    @PostMapping("/account/updateData/doctor")
    String updateDataDoctor(@ModelAttribute("doctor") Doctor doctor, Principal principal, Model model) throws Exception {
        //
        doctorService.updateDoctor(doctor, principal);
        //
        User user = userRepository.findByUsername(principal.getName());
        List<ReceptionHour> receptionHourForAccount;

        if (user.getUserRole().name().equals("ROLE_PATIENT")) {
            receptionHourForAccount = receptionHourService.getPatientReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("patient", patientRepository.findByUsername(principal.getName()));

        } else if (user.getUserRole().name().equals("ROLE_DOCTOR")) {
            receptionHourForAccount = receptionHourService.getDoctorReceptionHoursNoApi(principal.getName());
            model.addAttribute("receptionHourForAccounts", receptionHourForAccount);
            model.addAttribute("doctor", doctorRepository.findByUsername(principal.getName()));

        }
        model.addAttribute("user", user);
        return "redirect:/account";
    }

    @GetMapping("/patientcard")
    String ShowPatientCard(Model model, Principal principal){

        List<PatientCard> patientCards = patientCardRepository.findAllByPatientId(patientRepository.findByUsername(principal.getName()).getId());
        model.addAttribute("patientCards", patientCards);
        return "patientCard";
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
