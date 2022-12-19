package com.example.DTO;

public class SpecialisationDTO {

    private Long hospital_id;
    private String name;

    public SpecialisationDTO(Long hospital_id, String name) {
        this.hospital_id = hospital_id;
        this.name = name;
    }

    public Long getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(Long hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
