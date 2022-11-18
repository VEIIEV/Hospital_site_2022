package com.example.Hospital_site_2022.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_gen")
    @SequenceGenerator(name="patient_gen", sequenceName="patient_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient")
    private List<Diagnosis> diagnoses;



    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "residence")
    private String residence;

    @Column(name = "mail")
    private String mail;

    @Column(name = "number")
    private String number;

    public Patient(Long id, Hospital hospital, String name, String surname, String residence, String mail, String number) {
        this.id = id;
        this.hospital = hospital;
        this.name = name;
        this.surname = surname;
        this.residence = residence;
        this.mail = mail;
        this.number = number;
    }

    public Patient(Hospital hospital, String name, String surname, String residence, String mail, String number) {
        this.hospital = hospital;
        this.name = name;
        this.surname = surname;
        this.residence = residence;
        this.mail = mail;
        this.number = number;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}