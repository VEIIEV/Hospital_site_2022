package com.example.controller;


import com.example.Services.CleanDBService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public String deleteAllData() {
        cleanDBService.cleanDB();
        return "deal's done";
    }

    @GetMapping("/throwException")
    public String throwException() {
        try {throw  new Exception();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "congradulation, that you long waited exception");
        }

    }


}
