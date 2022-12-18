package com.example.controller;


import com.example.Entity.Hospital;
import com.example.Entity.User;
import com.example.Repository.HospitalRepository;
import com.example.Repository.UserRepository;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    UserDetailsServiceImpl userDetailsService;


    @GetMapping("/hello")
    public String hello(Model model) {

        Hospital hospital = hospitalRepository.findById(2L).orElseThrow(NoSuchElementException::new);

        model.addAttribute("hospital", hospital);
        return "helloPage";
    }

    @GetMapping("/")
    public String hello2(Model model) {

        Hospital hospital = hospitalRepository.findById(2L).orElseThrow(NoSuchElementException::new);

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
        model.addAttribute("user", user);
        return "patientCard";
    }

    @PostMapping("/patientCard/updateImg")
    String uploadImage(@RequestParam("image") MultipartFile multipartImage, Principal principal, Model model) throws Exception {

        userDetailsService.updateImg(multipartImage, principal);
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        System.out.println(user.getImage().length);
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
