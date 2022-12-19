package com.example.Services;

import com.example.DTO.HospitalDTO;
import com.example.Entity.Hospital;
import com.example.Repository.HospitalRepository;
import com.example.Utils.HospitalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final HospitalMapper mapper;

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalMapper mapper, HospitalRepository hospitalRepository) {
        this.mapper = mapper;
        this.hospitalRepository = hospitalRepository;
    }

    public ResponseEntity<HospitalDTO> createHospital(HospitalDTO hospitalDTO) {
        try {
            Hospital hospital= mapper.toHospital(hospitalDTO);
            hospitalRepository.save(hospital);
            Hospital createdHospital = hospitalRepository.findById(hospital.getId()).orElseThrow();
            return new ResponseEntity<>(mapper.toDTO(createdHospital), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
