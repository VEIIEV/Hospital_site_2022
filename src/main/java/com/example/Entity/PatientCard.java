package com.example.Entity;


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
    private Long doctorId;


    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public PatientCard(Patient patient, Set<Diagnosis> diagnoses, String prescribedTreatment, String assignDate, Long doctorId) {
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.prescribedTreatment = prescribedTreatment;
        this.assignDate = assignDate;
        this.doctorId = doctorId;
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
        return doctorId;
    }

    public void setDoctor_id(Long doctorId) {
        this.doctorId = doctorId;
    }
}
