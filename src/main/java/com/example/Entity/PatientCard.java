package com.example.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient_card")
@AllArgsConstructor
@NoArgsConstructor
public class PatientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diag_seq")
    @SequenceGenerator(name = "diag_gen", sequenceName = "diag_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;


    @Column(name = "prescribed_treatment")
    private String prescribedTreatment;

    @Column(name = "assign_date")
    private String assignDate;

    @Column(name = "doctor_id")
    private Long doctorId;






    public PatientCard(Patient patient, Diagnosis diagnoses, String prescribedTreatment, String assignDate, Long doctorId) {
        this.patient = patient;
        this.diagnosis = diagnoses;
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

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getDoctorId() {
        return doctorId;
    }


}
