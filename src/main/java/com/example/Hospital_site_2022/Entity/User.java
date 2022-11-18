package com.example.Hospital_site_2022.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class User {

    private String login;
    private String password;
    private String name;
    private String mail;
    private String number;
    private String token;


}
