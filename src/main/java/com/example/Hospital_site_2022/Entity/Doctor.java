package com.example.Hospital_site_2022.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    @SequenceGenerator(name="doctor_gen", sequenceName="doctor_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialisation_id")
    private Specialisation specialisation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private List<ReceptionHour> receptionHours;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "mail")
    private String mail;

    @Column(name = "number")
    private String number;

    @Column(name = "seniority")
    private String seniority;

    public Doctor(Specialisation specialisation, String name, String surname, String mail, String number, String seniority) {
        this.specialisation = specialisation;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.number = number;
        this.seniority = seniority;
    }

    public List<ReceptionHour> getReceptionHours() {
        return receptionHours;
    }

    public void setReceptionHours(List<ReceptionHour> receptionHours) {
        this.receptionHours = receptionHours;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}