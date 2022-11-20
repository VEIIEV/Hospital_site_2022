package com.example.Services;

import com.example.DTO.SpecialisationDTO;
import com.example.Entity.Specialisation;
import com.example.Utils.SpecialisationMapper;
import com.example.Repository.SpecialisationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialisationService {

    private final SpecialisationRepository specialisationRepository;
    private final SpecialisationMapper specialisationMapper;

    public SpecialisationService(SpecialisationRepository specialisationRepository, SpecialisationMapper specialisationMapper) {
        this.specialisationRepository = specialisationRepository;
        this.specialisationMapper = specialisationMapper;
    }


    public ResponseEntity<SpecialisationDTO> createSpecialisation(SpecialisationDTO specialisationDTO) {
        try {
            Specialisation specialisation = specialisationMapper.toSpecialisation(specialisationDTO);
            specialisationRepository.save(specialisation);
            SpecialisationDTO createdspecialisation = specialisationMapper.toDTO(specialisationRepository.findById(specialisation.getId()).orElseThrow());
            return new ResponseEntity<>(createdspecialisation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<SpecialisationDTO> updateSpecialisation(SpecialisationDTO specialisationDTO) {
        Specialisation specialisation = specialisationMapper.toSpecialisation(specialisationDTO);
        if (specialisationRepository.findById(specialisation.getId()).isPresent()) {
            try {
                specialisationRepository.save(specialisation);
                SpecialisationDTO createdspecialisation = specialisationMapper.toDTO(specialisationRepository.findById(specialisation.getId()).orElseThrow());
                return new ResponseEntity<>(createdspecialisation, HttpStatus.OK);
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

    public ResponseEntity<SpecialisationDTO> getSpecialisation(Long id) {
        if (specialisationRepository.findById(id).isPresent()) {
            SpecialisationDTO specialisationDTO = specialisationMapper.toDTO(specialisationRepository.findById(id).orElseThrow());
            return new ResponseEntity<>(specialisationDTO, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<SpecialisationDTO>> getAllSpecialisation(String sortMethod, String title) {
        List<Specialisation> specialisations;
        specialisations = switch (sortMethod) {
            case "asc" -> specialisationRepository.findAll(Sort.by(title).ascending());
            case "desc" -> specialisationRepository.findAll(Sort.by(title).descending());
            default -> specialisationRepository.findAll();
        };
        List<SpecialisationDTO> specialisationDTO=specialisations.stream().map(specialisationMapper::toDTO).toList();
        return new ResponseEntity<>(specialisationDTO, HttpStatus.OK);
    }
}
