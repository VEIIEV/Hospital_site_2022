package com.example.DTO;


import com.example.enums.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorDTO {


    private final UserRole userRole= UserRole.DOCTOR;
    private Long specialisation_id;
    private String name;
    private String surname;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String mail;
    private String number;
    private String seniority;


    public DoctorDTO(Long specialisation_id, String name, String surname, String mail, String number, String seniority) {
        this.specialisation_id = specialisation_id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.number = number;
        this.seniority = seniority;
    }

    public DoctorDTO(Long specialisation_id, String name, String surname, String login, String password, String mail, String number, String seniority) {
        this.specialisation_id = specialisation_id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.number = number;
        this.seniority = seniority;
    }

    public DoctorDTO(){
        this.name ="no DATA in DB";
    }

    public Long getSpecialisation_id() {
        return specialisation_id;
    }

    public void setSpecialisation_id(Long specialisation_id) {
        this.specialisation_id = specialisation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
