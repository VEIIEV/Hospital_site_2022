package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Repository.DiagnosisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    final private DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }


    public  ResponseEntity<Diagnosis> createDisease(Diagnosis diagnosis) {


        diagnosisRepository.save(diagnosis);
        Diagnosis createdPatientCard = diagnosisRepository.findById(diagnosis.getId()).get();
        return new ResponseEntity<>(createdPatientCard, HttpStatus.OK);

    }
}
