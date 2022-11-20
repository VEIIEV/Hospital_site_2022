package com.example.Utils;

import com.example.DTO.DoctorDTO;
import com.example.Entity.Doctor;
import com.example.Entity.Specialisation;
import com.example.Repository.SpecialisationRepository;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    private final SpecialisationRepository specialisationRepository;

    public DoctorMapper(SpecialisationRepository specialisationRepository) {
        this.specialisationRepository = specialisationRepository;
    }

    public DoctorDTO toDTO(Doctor doctor) {
        return new DoctorDTO(
                doctor.getSpecialisation().getId(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getMail(),
                doctor.getNumber(),
                doctor.getSeniority()
        );
    }

    public Doctor toDoctor(DoctorDTO doctorDTO) {
        Specialisation specialisation = specialisationRepository.findById(doctorDTO.getSpecialisation_id()).get();
        return new Doctor(
                specialisation,
                doctorDTO.getName(),
                doctorDTO.getSurname(),
                doctorDTO.getMail(),
                doctorDTO.getNumber(),
                doctorDTO.getSeniority()
        );
    }


}
