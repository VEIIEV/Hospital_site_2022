package com.example.Services;


import com.example.DTO.PatientCardDTO;
import com.example.DTO.UniversalDTO;
import com.example.Entity.PatientCard;
import com.example.Repository.PatientCardRepository;
import com.example.Utils.PatientCardMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper mapper;

    public PatientCardService(PatientCardRepository patientCardRepository, PatientCardMapper mapper) {
        this.patientCardRepository = patientCardRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<PatientCardDTO> createPatientCard(@RequestBody PatientCardDTO patientCardDTO) {
            PatientCard patientCard = mapper.toPatientCard(patientCardDTO);
            patientCardRepository.save(patientCard);
            PatientCard createdPatientCard = patientCardRepository.findById(patientCard.getId()).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(createdPatientCard), HttpStatus.OK);



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


    public ResponseEntity<Set<PatientCardDTO>> getPatientCardByDoctorId(Long dockorId) {
        Set<PatientCard> patientCards = patientCardRepository.findAllByDoctorId(dockorId);
        Set<PatientCardDTO> patientCardDTOS= patientCards.stream().map(mapper::toDTO).collect(Collectors.toSet());

        return new ResponseEntity<>(patientCardDTOS, HttpStatus.OK) ;
    }

    public ResponseEntity<Set<PatientCardDTO>> getPatientCardByPatientId(Long patientId) {
        Set<PatientCard> patientCards = patientCardRepository.findAllByPatientId(patientId);
        Set<PatientCardDTO> patientCardDTOS= patientCards.stream().map(mapper::toDTO).collect(Collectors.toSet());

        return new ResponseEntity<>(patientCardDTOS, HttpStatus.OK) ;
    }

    public ResponseEntity<Set<UniversalDTO>> countVisetPerDoctor() {
        return new ResponseEntity<>( patientCardRepository.findByCountedDoctorId(), HttpStatus.OK);
    }

    public ResponseEntity<Set<UniversalDTO>> countDiagnosisPerPatient() {

        return  new ResponseEntity<>(patientCardRepository.findByCountedDiagnoses(), HttpStatus.OK);
    }
}
