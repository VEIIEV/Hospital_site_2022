package com.example.DTO;

import com.example.JSONView.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class UniversalDTO {

    @JsonView(Views.Doctorstat.class)
    private Long doctor_id;

    @JsonView(Views.Doctorstat.class)
    private Long count;

    @JsonView(Views.Diagnosis.class)
    private Long diagnosis_id;

    @JsonView(Views.Diagnosis.class)
    private int size;

    public UniversalDTO(Long diagnosis_id, int size) {
        this.diagnosis_id = diagnosis_id;
        this.size = size;
    }

    public UniversalDTO(Long id, Long count) {
        this.doctor_id = id;
        this.count = count;
    }

    public Long getDiagnosis_id() {
        return diagnosis_id;
    }

    public void setDiagnosis_id(Long diagnosis_id) {
        this.diagnosis_id = diagnosis_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
