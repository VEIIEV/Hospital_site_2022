package com.example.Hospital_site_2022.Utils;

import com.example.Hospital_site_2022.DTO.HospitalDTO;
import com.example.Hospital_site_2022.Entity.Hospital;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapper {

    public HospitalDTO toDTO(Hospital hospital) {
        return new HospitalDTO(
                hospital.getName(),
                hospital.getAddress(),
                hospital.getMail(),
                hospital.getNumber());
    }

    public Hospital toHospital(HospitalDTO hospitalDTO){
        return new Hospital(
                hospitalDTO.getName(),
                hospitalDTO.getAddress(),
                hospitalDTO.getMail(),
                hospitalDTO.getNumber());
    }


}
