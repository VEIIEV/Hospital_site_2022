package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.DTO.HospitalDTO;
import com.example.Hospital_site_2022.Entity.Hospital;
import com.example.Hospital_site_2022.Repository.HospitalRepository;
import com.example.Hospital_site_2022.Utils.HospitalMapper;
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
