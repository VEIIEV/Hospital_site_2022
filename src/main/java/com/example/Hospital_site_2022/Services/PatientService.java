package com.example.Hospital_site_2022.Services;


import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.PatientRepository;
import com.example.Hospital_site_2022.Utils.PatientMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        } catch (Exception e) {
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
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

        if (!title.isEmpty()) {

            switch (sortMethod) {
                case "asc":
                    patients = patientRepository.findAll(Sort.by(title).ascending());
                    break;
                case "desc":
                    patients = patientRepository.findAll(Sort.by(title).descending());
                    break;
                default:
                    patients = new ArrayList<>();
            }
        } else {
            patients = patientRepository.findAll();
        }
        List<PatientDTO> patientDTOS = patients.stream().map(patientMapper::toDTOFromPatient).toList();
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }


}
