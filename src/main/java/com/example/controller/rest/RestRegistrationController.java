package com.example.controller.rest;


import com.example.Entity.User;
import com.example.Services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/registration")
@AllArgsConstructor
@Validated
public class RestRegistrationController {

    UserDetailsServiceImpl userDetailsService;


    @PostMapping("/signup")
    public ResponseEntity<User>  create(@Valid @RequestBody User user){

        if(userDetailsService.saveUser(user)==false) return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
