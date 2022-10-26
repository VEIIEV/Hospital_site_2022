package com.example.Hospital_site_2022.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
@Data
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;
}