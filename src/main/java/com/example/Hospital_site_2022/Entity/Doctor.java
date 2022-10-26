package com.example.Hospital_site_2022.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.swing.text.html.HTMLDocument;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    //подвязали расписание
    @OneToMany(mappedBy = "doctor")
    Set<ReceptionHours> receptionHours = new java.util.LinkedHashSet<>();

    //подвязали пациентов
    @ManyToMany
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Patient> patients;

    //Таблица = врача (id, имя, контактные данные, список пациентов,кол-во визитов???...)
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
    // многие ко многим
    //@ManyToMany(mappedBy = "doctors")
    //private Set<Hospital> hospitals;



}