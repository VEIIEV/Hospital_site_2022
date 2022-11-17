package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.DTO.PatientDTO;
import com.example.Hospital_site_2022.Entity.Patient;
import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Repository.SpecialisationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialisationService {

    private final SpecialisationRepository specialisationRepository;

    public SpecialisationService(SpecialisationRepository specialisationRepository) {
        this.specialisationRepository = specialisationRepository;
    }


    public ResponseEntity<Specialisation> createSpecialisation(Specialisation specialisation) {
        try {
            specialisationRepository.save(specialisation);
            return new ResponseEntity<>(
                    specialisationRepository.findById(specialisation.getId()).orElseThrow(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Specialisation> updateSpecialisation(Specialisation specialisation) {
        if (specialisationRepository.findById(specialisation.getId()).isPresent()) {
            try {
                specialisationRepository.save(specialisation);
                return new ResponseEntity<>(specialisationRepository.findById(specialisation.getId()).orElseThrow(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Integer> deleteSpecialisation(Long id) {
        if (specialisationRepository.findById(id).isPresent()) {
            try {
                specialisationRepository.deleteById(id);
                return new ResponseEntity<>(1, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Specialisation> getSpecialisation(Long id) {
        if (specialisationRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(specialisationRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Specialisation>> getAllSpecialisation(String sortMethod, String title) {
        List<Specialisation> specialisations;
        specialisations = switch (sortMethod) {
            case "asc" -> specialisationRepository.findAll(Sort.by(title).ascending());
            case "desc" -> specialisationRepository.findAll(Sort.by(title).descending());
            default -> specialisationRepository.findAll();
        };

        return new ResponseEntity<>(specialisations, HttpStatus.OK);
    }
}
