package com.example.Hospital_site_2022.Utils;


import com.example.Hospital_site_2022.DTO.PatientCardDTO;
import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.PatientCard;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.DiagnosisRepository;
import com.example.Hospital_site_2022.Repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PatientCardMapper {

    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;

    public PatientCardMapper(PatientRepository patientRepository, DiagnosisRepository diagnosisRepository) {
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    public PatientCard toPatientCard(PatientCardDTO patientCardDTO) {
        Patient patient = patientRepository.findById(patientCardDTO.getPatientId()).orElseThrow();
        Set<Diagnosis> diagnoses = diagnosisRepository.findAllByNameIn(patientCardDTO.getDiagnoses());
        return new PatientCard(
                patient,
                diagnoses,
                patientCardDTO.getPrescribedTreatment(),
                patientCardDTO.getAssignDate(),
                patientCardDTO.getDoctorId()
        );
    }


    public PatientCardDTO toDTO(PatientCard patientCard){
        Set<String> diagnoses = diagnosisRepository.findAllByPatientCards_id(patientCard.getId()).
                stream().map(Diagnosis::getName).collect(Collectors.toSet());
        return new PatientCardDTO(
                patientCard.getId(),
                patientCard.getPatient().getId(),
                patientCard.getDoctor_id(),
                patientCard.getPrescribedTreatment(),
                patientCard.getAssignDate(),
                diagnoses
        );
    }


}
