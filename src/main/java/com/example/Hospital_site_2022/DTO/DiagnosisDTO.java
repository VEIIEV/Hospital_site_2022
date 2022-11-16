package com.example.Hospital_site_2022.DTO;

import com.example.Hospital_site_2022.Entity.Patient;

import javax.persistence.Column;

public class DiagnosisDTO {

    private Long id;

    private Long patientId;

    private String name;

    private String prescribedTreatment;

    private String assignDate;

    public DiagnosisDTO(Long id, Long patientId, String name, String prescribedTreatment, String assignDate) {
        this.id = id;
        this.patientId = patientId;
        this.name = name;
        this.prescribedTreatment = prescribedTreatment;
        this.assignDate = assignDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }
}
