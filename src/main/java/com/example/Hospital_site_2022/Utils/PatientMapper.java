package com.example.Hospital_site_2022.Utils;

import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    private final HospitalRepository hospitalRepository;

    public PatientMapper(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public PatientDTO upDTOFromPatient(Patient patient){
        return new PatientDTO(
                patient.getHospital().getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getResidence(),
                patient.getMail(),
                patient.getNumber()
        );
    }

    public Patient toPatientFromDTO(PatientDTO patientDTO){
        Hospital hospital= hospitalRepository.findById(patientDTO.getHospitalId()).orElseThrow();
        return new Patient(
                hospital,
                patientDTO.getName(),
                patientDTO.getSurname(),
                patientDTO.getResidence(),
                patientDTO.getMail(),
                patientDTO.getNumber()
        );
    }

    public PatientDTO toDTOFromPatientDTOWithId(PatientDTOWithId patientDTOWithId){
        return new PatientDTO(
                patientDTOWithId.getHospitalId(),
                patientDTOWithId.getName(),
                patientDTOWithId.getSurname(),
                patientDTOWithId.getResidence(),
                patientDTOWithId.getMail(),
                patientDTOWithId.getNumber()
        );
    }

    public Patient toPatientFromPatientDTOWithId(PatientDTOWithId patientDTOWithId){
        Hospital hospital= hospitalRepository.findById(patientDTOWithId.getHospitalId()).orElseThrow();

        return new Patient(

                patientDTOWithId.getId(),
                hospital,
                patientDTOWithId.getName(),
                patientDTOWithId.getSurname(),
                patientDTOWithId.getResidence(),
                patientDTOWithId.getMail(),
                patientDTOWithId.getNumber()
        );
    }

}
