package com.example.Services;


import com.example.DTO.PatientDTO;
import com.example.DTO.PatientDTOWithId;
import com.example.Entity.Patient;
import com.example.Repository.PatientRepository;
import com.example.Utils.PatientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@Service
public class PatientService {


    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public ResponseEntity<PatientDTO> createPatient(PatientDTO patientDTO) {
        try {

            Patient patient = patientMapper.toPatientFromDTO(patientDTO);
            patientRepository.save(patient);
            Patient createdPatient = patientRepository.findById(patient.getId()).orElseThrow();
            return new ResponseEntity<>(patientMapper.toDTOFromPatient(createdPatient), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


    public ResponseEntity<PatientDTO> updatePatient(PatientDTOWithId patientDTOWithId) {


        if (patientRepository.findById(patientDTOWithId.getId()).isPresent()) {
            try {
                Patient patient = patientMapper.toPatientFromPatientDTOWithId(patientDTOWithId);
                patientRepository.save(patient);
                Patient createdPatient = patientRepository.findById(patient.getId()).orElseThrow();
                return new ResponseEntity<>(patientMapper.toDTOFromPatient(createdPatient), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> deletePatient(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            patientRepository.deleteById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<PatientDTO> getPatient(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            Patient patient = patientRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(patientMapper.toDTOFromPatient(patient), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    //выводим всех пользователей по порядку заданному sortMethod,
    //применяем сортировку к ячейке под названием title
    public ResponseEntity<List<PatientDTO>> getAllPatient(String sortMethod, String title) {


        List<Patient> patients;
        //if(title.isEmpty()){ title="id";}

        patients = switch (sortMethod) {
            case "asc" -> patientRepository.findAll(Sort.by(title).ascending());
            case "desc" -> patientRepository.findAll(Sort.by(title).descending());
            default -> patientRepository.findAll();
        };

        List<PatientDTO> patientDTOS = patients.stream().map(patientMapper::toDTOFromPatient).toList();
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }


    public void updatePatientData(Patient patient, Principal principal) {

        Patient patient1 = patientRepository.findPatientsByUsername(principal.getName()).get();
        if (patient.getName() != null) patient1.setName(patient.getName());
        if (patient.getSurname()!=null) patient1.setSurname(patient.getSurname());
        if (patient.getResidence()!=null) patient1.setResidence(patient.getResidence());
        if (patient.getNumber()!=null) patient1.setNumber(patient.getNumber());
        patientRepository.save(patient1);
    }
}
