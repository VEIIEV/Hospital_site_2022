package com.example.Entity;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "patientId")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Patient extends User {

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientCard> diagnoses;

    @Column(name = "surname")
    private String surname;

    @Column(name = "residence")
    private String residence;

    public Patient(Long id, String login, String password, String token, String name, String mail, String number,  Hospital hospital,  String surname, String residence) {
        super(id, login, password, token, name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
    }

    public Patient( String login, String password, String token, String name, String mail, String number,  Hospital hospital,  String surname, String residence) {
        super(login, password, token, name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
    }

    public Patient(Hospital hospital, String name, String surname, String residence, String mail, String number) {
        super(name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
    }

    public Patient(Long id, Hospital hospital, String name, String surname, String residence, String mail, String number) {
        super(id, name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
    }

    public Patient() {
        super();
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}