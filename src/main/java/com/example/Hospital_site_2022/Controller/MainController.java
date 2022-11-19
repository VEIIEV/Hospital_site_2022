package com.example.Hospital_site_2022.Controller;


import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
@AllArgsConstructor
public class MainController {


    @GetMapping("/hello")
    public String helloSite() {

            return "Requested data is null";
    }










}
