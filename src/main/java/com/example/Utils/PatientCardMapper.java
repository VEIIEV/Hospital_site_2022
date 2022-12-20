package com.example.Utils;


import com.example.Entity.Patient;
import com.example.Entity.PatientCard;
import com.example.Repository.PatientRepository;
import com.example.DTO.PatientCardDTO;
import com.example.Entity.Diagnosis;
import com.example.Repository.DiagnosisRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
        Optional<Diagnosis> diagnosisOptional = diagnosisRepository.findByName(patientCardDTO.getDiagnosis());
        //если диагноз есть, то всё норм, если нет, то создаём
        Diagnosis diagnosis;
        if (diagnosisOptional.isPresent()) {
             diagnosis = diagnosisOptional.get();
        }
        else{
             diagnosis = new Diagnosis();
            diagnosis.setName(patientCardDTO.getDiagnosis());
            diagnosisRepository.save(diagnosis);
        }

        return new PatientCard(
                patient,
                diagnosis,
                patientCardDTO.getPrescribedTreatment(),
                patientCardDTO.getAssignDate(),
                patientCardDTO.getDoctorId()
        );
    }


    public PatientCardDTO toDTO(PatientCard patientCard) {
        String diagnosis = diagnosisRepository.findByName(patientCard.getDiagnosis().getName()).get().getName();
        return new PatientCardDTO(
                patientCard.getId(),
                patientCard.getPatient().getId(),
                patientCard.getDoctorId(),
                patientCard.getPrescribedTreatment(),
                patientCard.getAssignDate(),
                diagnosis
        );
    }


}
