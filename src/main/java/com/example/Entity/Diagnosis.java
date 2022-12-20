package com.example.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
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

    @Column(name = "name")
    private String name;

    @Column(name = "discription")
    private String discription;

    @OneToMany(mappedBy = "diagnosis", orphanRemoval = true)
    private Set<PatientCard> patientCards = new LinkedHashSet<>();

    public void setPatientCards(Set<PatientCard> patientCards) {
        this.patientCards = patientCards;
    }

    public Set<PatientCard> getPatientCards() {
        return patientCards;
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
