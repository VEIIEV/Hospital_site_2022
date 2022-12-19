package com.example.Services;

import com.example.Repository.HospitalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CleanDBService {
    final private HospitalRepository hospitalRepository;

    public CleanDBService(
                          HospitalRepository hospitalRepository
                          ) {

        this.hospitalRepository = hospitalRepository;

    }

    @Transactional
    public boolean cleanDB() {

        hospitalRepository.deleteAllBy();

        return true;
    }

}
