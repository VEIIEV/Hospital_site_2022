package com.example.Controller;


import com.example.Services.CleanDBService;
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

    CleanDBService cleanDBService;

    @GetMapping("/hello")
    public String helloSite() {

            return "Requested data is null";
    }

    @GetMapping("/deleteAllDATA")
    public String deleteAllData(){
        cleanDBService.cleanDB();
        return "deal's done";
    }








}
