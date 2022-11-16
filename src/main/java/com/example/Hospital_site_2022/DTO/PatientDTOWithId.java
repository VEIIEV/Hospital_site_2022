package com.example.Hospital_site_2022.DTO;

public class PatientDTOWithId extends PatientDTO{

    private Long id;

    public PatientDTOWithId(Long id, Long hospitalId, String name, String surname, String residence, String mail, String number) {
        super(hospitalId, name, surname, residence, mail, number);
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
