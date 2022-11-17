package com.example.Hospital_site_2022.DTO;

public class ReceptionHourDTO {

    private Long doctor_id;

    private String dateTime;

    private int status;

    public ReceptionHourDTO(Long doctor_id, String dateTime, int status) {
        this.doctor_id = doctor_id;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}