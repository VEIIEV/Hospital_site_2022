package com.example.Hospital_site_2022.Utils;

import com.example.Hospital_site_2022.DTO.SpecialisationDTO;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

@Component
public class SpecialisationMapper {

    private final HospitalRepository hospitalRepository;


    public SpecialisationMapper(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Specialisation toSpecialisation(SpecialisationDTO specialisationDTO){

        Hospital hospital = hospitalRepository.findById(specialisationDTO.getHospital_id()).get();
        return new Specialisation(
                hospital,
                specialisationDTO.getName()
        );
    }

    public SpecialisationDTO toDTO(Specialisation specialisation){
        return new SpecialisationDTO(
                specialisation.getHospital().getId(),
                specialisation.getName()
        );
    }

}
