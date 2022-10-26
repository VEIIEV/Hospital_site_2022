package com.example.Hospital_site_2022.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name= "residence")
    private String residence;


    // @ManyToMany
    // @JoinTable(
    //         name ="doctors_list",
    //         joinColumns =  @JoinColumn(name = "doctor_id"),
    //         inverseJoinColumns = @JoinColumn(name = "hospital_id"))
    // private Set<Doctor> doctors = new java.util.LinkedHashSet<>();

    @ManyToMany()
    @JoinTable( name = "patient_diagnoses",
            joinColumns = @JoinColumn(name= "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set <Diagnosis> diagnoses = new java.util.LinkedHashSet<>();

    @ManyToMany(mappedBy ="patients")
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    Set<ReceptionHours> receptionHours = new java.util.LinkedHashSet<>();

    //Check Таблица = карта пациента (id, имя, контактные данные, прописка, диагноз

}