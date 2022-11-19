package com.example.Hospital_site_2022.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "diagnosis")
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diag_gen")
    @SequenceGenerator(name = "diag_gen", sequenceName = "diag_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "name")
    private String name;

    @Column(name = "prescribed_treatment")
    private String prescribedTreatment;

    @Column(name = "assign_date")
    private String assignDate;

    public Diagnosis(Patient patient, String name, String prescribedTreatment, String assignDate) {
        this.patient = patient;
        this.name = name;
        this.prescribedTreatment = prescribedTreatment;
        this.assignDate = assignDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrescribedTreatment() {
        return prescribedTreatment;
    }

    public void setPrescribedTreatment(String prescribedTreatment) {
        this.prescribedTreatment = prescribedTreatment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
