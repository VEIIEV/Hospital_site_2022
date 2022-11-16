package com.example.Hospital_site_2022.Services;


import com.example.Hospital_site_2022.DTO.DiagnosisDTO;
import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.Entity.Diagnosis;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.DiagnosisRepository;
import com.example.Hospital_site_2022.Utils.DiagnosisMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper mapper;

    public DiagnosisService(DiagnosisRepository diagnosisRepository, DiagnosisMapper mapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<DiagnosisDTO> createDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) {
        try {
            Diagnosis diagnosis = mapper.toDiagnosis(diagnosisDTO);
            diagnosisRepository.save(diagnosis);
            Diagnosis createdDiagnosis = diagnosisRepository.findById(diagnosis.getId()).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(createdDiagnosis), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<DiagnosisDTO> updateDiagnosis(DiagnosisDTO diagnosisDTO) {
        if (diagnosisRepository.findById(diagnosisDTO.getId()).isPresent()) {
            try {
                Diagnosis diagnosis = mapper.toDiagnosis(diagnosisDTO);
                diagnosisRepository.save(diagnosis);
                Diagnosis createdDiagnosis = diagnosisRepository.findById(diagnosis.getId()).orElseThrow();
                return new ResponseEntity<>(mapper.toDTO(createdDiagnosis), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> deleteDiagnosis(Long id) {
        if (diagnosisRepository.findById(id).isPresent()) {
            diagnosisRepository.deleteById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<DiagnosisDTO> getDiagnosis(Long id){
        if (diagnosisRepository.findById(id).isPresent()) {
            Diagnosis diagnosis = diagnosisRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(diagnosis), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<DiagnosisDTO>> getAllDiagnosis(String sortMethod, String title) {

        List<Diagnosis> diagnoses;

        if (!title.isEmpty()) {

            switch (sortMethod) {
                case "asc":
                    diagnoses = diagnosisRepository.findAll(Sort.by(title).ascending());
                    break;
                case "desc":
                    diagnoses = diagnosisRepository.findAll(Sort.by(title).descending());
                    break;
                default:
                    diagnoses = new ArrayList<>();
            }
        } else {
            diagnoses= diagnosisRepository.findAll();
        }
        List<DiagnosisDTO> diagnosisDTOS = diagnoses.stream().map(mapper::toDTO).toList();
        return new ResponseEntity<>(diagnosisDTOS, HttpStatus.OK);
    }




}
