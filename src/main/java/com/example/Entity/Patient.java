package com.example.Entity;

import com.example.enums.UserRole;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private Set<ReceptionHour> receptionHours = new LinkedHashSet<>();

    public Patient(User user) {
        super(user);
        setUserRole(UserRole.ROLE_PATIENT);
    }

    public Patient() {

    }

    public Set<ReceptionHour> getReceptionHours() {
        return receptionHours;
    }

    public void setReceptionHours(Set<ReceptionHour> receptionHours) {
        this.receptionHours = receptionHours;
    }

    public Patient(Long id, String login, String password, String token, String name, String mail, String number, Hospital hospital, String surname, String residence) {
        super(id, login, password, token, name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
    }

    public Patient(Hospital hospital,  String name,String surname, String login, String password, String token, String residence, String mail, String number) {
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

    public Patient(UserRole userRole, String login, String password, String name, String mail, String number, Hospital hospital, String surname, String residence) {
        super(userRole, login, password, name, mail, number);
        this.hospital = hospital;
        this.surname = surname;
        this.residence = residence;
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

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
        return authorities;
    }
}