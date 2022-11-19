package com.example.Hospital_site_2022.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "patient_card")
@AllArgsConstructor
@NoArgsConstructor
public class PatientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diag_gen")
    @SequenceGenerator(name = "diag_gen", sequenceName = "diag_seq")
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany
    @JoinTable(name = "patient_card_diagnoses",
            joinColumns = @JoinColumn(name = "patient_card_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnosis>  diagnoses = new LinkedHashSet<>();

    @Column(name = "prescribed_treatment")
    private String prescribedTreatment;

    @Column(name = "assign_date")
    private String assignDate;

    @Column(name = "doctor_id")
    private Long doctor_id;


    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public PatientCard(Patient patient, Set<Diagnosis> diagnoses, String prescribedTreatment, String assignDate, Long doctor_id) {
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.prescribedTreatment = prescribedTreatment;
        this.assignDate = assignDate;
        this.doctor_id = doctor_id;
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

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }
}
