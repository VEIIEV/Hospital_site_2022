package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Diagnosis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface   DiagnosisRepository extends CrudRepository<Diagnosis, Long> {

    Optional<Diagnosis> findById(Long id );

    List<Diagnosis> findAll();




}
