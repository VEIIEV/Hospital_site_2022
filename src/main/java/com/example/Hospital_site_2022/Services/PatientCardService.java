package com.example.Hospital_site_2022.Services;


import com.example.Hospital_site_2022.DTO.PatientCardDTO;
import com.example.Hospital_site_2022.Entity.PatientCard;
import com.example.Hospital_site_2022.Repository.PatientCardRepository;
import com.example.Hospital_site_2022.Utils.PatientCardMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper mapper;

    public PatientCardService(PatientCardRepository patientCardRepository, PatientCardMapper mapper) {
        this.patientCardRepository = patientCardRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<PatientCardDTO> createPatientCard(@RequestBody PatientCardDTO patientCardDTO) {
        try {
            PatientCard patientCard = mapper.toPatientCard(patientCardDTO);
            patientCardRepository.save(patientCard);
            PatientCard createdPatientCard = patientCardRepository.findById(patientCard.getId()).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(createdPatientCard), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<PatientCardDTO> updatePatientCard(PatientCardDTO patientCardDTO) {
        if (patientCardRepository.findById(patientCardDTO.getId()).isPresent()) {
            try {
                PatientCard patientCard = mapper.toPatientCard(patientCardDTO);
                patientCardRepository.save(patientCard);
                PatientCard createdPatientCard = patientCardRepository.findById(patientCard.getId()).orElseThrow();
                return new ResponseEntity<>(mapper.toDTO(createdPatientCard), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> deletePatientCard(Long id) {
        if (patientCardRepository.findById(id).isPresent()) {
            patientCardRepository.deleteById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<PatientCardDTO> getPatientCard(Long id) {
        if (patientCardRepository.findById(id).isPresent()) {
            PatientCard patientCard = patientCardRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(patientCard), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<PatientCardDTO>> getAllPatientCard(String sortMethod, String title) {

        List<PatientCard> diagnoses;
        diagnoses = switch (sortMethod) {
            case "asc" -> patientCardRepository.findAll(Sort.by(title).ascending());
            case "desc" -> patientCardRepository.findAll(Sort.by(title).descending());
            default -> patientCardRepository.findAll();
        };

        List<PatientCardDTO> patientCardDTOS = diagnoses.stream().map(mapper::toDTO).toList();
        return new ResponseEntity<>(patientCardDTOS, HttpStatus.OK);
    }


}
