package com.example.DTO;

import java.util.Set;

public class PatientCardDTO {

    private Long id;

    private Long patientId;

    private Long doctorId;

    private String prescribedTreatment;

    private String assignDate;

    private String diagnosis;

    public PatientCardDTO(Long id, Long patientId, Long doctorId, String prescribedTreatment, String assignDate, String diagnosis) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescribedTreatment = prescribedTreatment;
        this.assignDate = assignDate;
        this.diagnosis = diagnosis;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
