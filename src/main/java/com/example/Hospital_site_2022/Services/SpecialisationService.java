package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.Entity.Specialisation;
import com.example.Hospital_site_2022.Repository.SpecialisationRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialisationService {

    private SpecialisationRepository specialisationRepository;

    public int saveSpecialisation(Specialisation specialisation){

        specialisationRepository.save(specialisation);
        return 1;
    }

}
