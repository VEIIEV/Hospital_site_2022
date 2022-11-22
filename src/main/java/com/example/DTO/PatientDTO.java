package com.example.DTO;

import com.example.enums.UserRole;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientDTO {

    private Long  hospitalId;
    private String name;
    private String surname;
    private String token;
    private String residence;
    private String number;

    final private UserRole userRole= UserRole.PATIENT;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String mail;

    public PatientDTO(Long hospitalId, String name, String surname, String residence, String mail, String number) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.surname = surname;
        this.residence = residence;
        this.mail = mail;
        this.number = number;
    }

    public PatientDTO(Long hospitalId, String name, String surname, String residence, String number, String login, String password, String mail) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.surname = surname;
        this.residence = residence;
        this.number = number;
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public PatientDTO(Long hospitalId, String name, String surname, String login, String password, String token, String residence, String mail, String number) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.token=token;
        this.residence = residence;
        this.mail = mail;
        this.number = number;
    }

    public PatientDTO(){
        this.name ="no DATA in DB";
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
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

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
