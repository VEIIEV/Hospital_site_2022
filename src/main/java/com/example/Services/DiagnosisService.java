package com.example.Services;

import com.example.Repository.DiagnosisRepository;
import com.example.Entity.Diagnosis;
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
