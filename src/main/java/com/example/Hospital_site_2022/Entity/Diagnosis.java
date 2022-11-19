package com.example.Hospital_site_2022.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnosis_gen")
    @SequenceGenerator(name = "diagnosis_gen", sequenceName = "diagnosis_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<PatientCard> patientCards = new LinkedHashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "discription")
    private String discription;

    public Set<PatientCard> getPatientCards() {
        return patientCards;
    }

    public void setPatientCards(Set<PatientCard> patientCards) {
        this.patientCards = patientCards;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
