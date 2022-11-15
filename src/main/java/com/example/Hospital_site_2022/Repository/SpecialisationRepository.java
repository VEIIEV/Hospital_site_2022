package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Specialisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialisationRepository extends CrudRepository<Specialisation, Long> {
}
