package com.example.Hospital_site_2022.DTO;

import javax.persistence.Column;

public class PatientDTO {

    private Long hospitalId;

    private String name;

    private String surname;

    private String residence;

    private String mail;

    private String number;

    public PatientDTO(Long hospitalId, String name, String surname, String residence, String mail, String number) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.surname = surname;
        this.residence = residence;
        this.mail = mail;
        this.number = number;
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
}
