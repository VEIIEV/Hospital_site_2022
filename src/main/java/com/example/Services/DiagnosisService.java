package com.example.Services;

import com.example.Entity.Diagnosis;
import com.example.Repository.DiagnosisRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiagnosisService {

    final private DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }


    public ResponseEntity<Diagnosis> createDisease(Diagnosis diagnosis) {


        diagnosisRepository.save(diagnosis);
        Diagnosis createdPatientCard = diagnosisRepository.findById(diagnosis.getId()).get();
        return new ResponseEntity<>(createdPatientCard, HttpStatus.OK);

    }

    public Diagnosis getDiagnosisById(Long id) throws NoSuchElementException {
        return  diagnosisRepository.findById(id).orElseThrow();
    }


    public List<Diagnosis> getAllDiagnosis(String sortMethod, String title) {
        List<Diagnosis> diagnoses;
        diagnoses = switch (sortMethod) {
            case "asc" -> diagnosisRepository.findAll(Sort.by(title).ascending());
            case "desc" -> diagnosisRepository.findAll(Sort.by(title).descending());
            default -> diagnosisRepository.findAll();
        };
        return diagnoses;
    }

    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();

    }
}
