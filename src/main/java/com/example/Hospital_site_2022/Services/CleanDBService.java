package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.Repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CleanDBService {
    final private DiagnosisRepository diagnosisRepository;
    final private DoctorRepository doctorRepository;
    final private HospitalRepository hospitalRepository;
    final private PatientCardRepository patientCardRepository;
    final private PatientRepository patientRepository;
    final private ReceptionHourRepository receptionHourRepository;
    final private SpecialisationRepository specialisationRepository;

    public CleanDBService(DiagnosisRepository diagnosisRepository,
                          DoctorRepository doctorRepository,
                          HospitalRepository hospitalRepository,
                          PatientCardRepository patientCardRepository,
                          PatientRepository patientRepository,
                          ReceptionHourRepository receptionHourRepository,
                          SpecialisationRepository specialisationRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientCardRepository = patientCardRepository;
        this.patientRepository = patientRepository;
        this.receptionHourRepository = receptionHourRepository;
        this.specialisationRepository = specialisationRepository;
    }

    @Transactional
    public boolean cleanDB() {

        diagnosisRepository.deleteAllBy();

        return true;
    }

}
