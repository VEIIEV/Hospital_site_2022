package com.example.Hospital_site_2022.Utils;


import com.example.Hospital_site_2022.DTO.DiagnosisDTO;
import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisMapper {

    private final PatientRepository patientRepository;

    public DiagnosisMapper(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Diagnosis toDiagnosis(DiagnosisDTO diagnosisDTO) {
        Patient patient = patientRepository.findById(diagnosisDTO.getPatientId()).orElseThrow();
        return new Diagnosis(
                patient,
                diagnosisDTO.getName(),
                diagnosisDTO.getPrescribedTreatment(),
                diagnosisDTO.getAssignDate()
        );
    }


    public DiagnosisDTO toDTO(Diagnosis diagnosis){
        return new DiagnosisDTO(
                diagnosis.getId(),
                diagnosis.getPatient().getId(),
                diagnosis.getName(),
                diagnosis.getPrescribedTreatment(),
                diagnosis.getAssignDate()
        );
    }


}
