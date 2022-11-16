package com.example.Hospital_site_2022.Services;


import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.DTO.PatientDTOWithId;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Repository.PatientRepository;
import com.example.Hospital_site_2022.Utils.PatientMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            return new ResponseEntity<>(patientMapper.upDTOFromPatient(createdPatient), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //не обновляет, а создаёт потому что я вкладываю в метод save()
    //объект patient без id, нужно в мапере его задать
    //если запрашивать через объект
    public ResponseEntity<PatientDTO> updatePatient(PatientDTOWithId patientDTOWithId) {




        if (patientRepository.findById(patientDTOWithId.getId()).isPresent()) {
            try {
                Patient patient = patientMapper.toPatientFromPatientDTOWithId(patientDTOWithId);
                patientRepository.save(patient);
                Patient createdPatient = patientRepository.findById(patient.getId()).orElseThrow();
                return new ResponseEntity<>(patientMapper.upDTOFromPatient(createdPatient), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public int deletePatient(Long id) {
        patientRepository.deleteById(id);
        return 1;
    }


    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }


}
