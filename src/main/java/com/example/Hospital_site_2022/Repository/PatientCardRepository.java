package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.PatientCard;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, Long> {

    Optional<PatientCard> findById(Long id );

    List<PatientCard> findAll();

    @Query("select p from PatientCard p inner join p.diagnoses diagnoses where diagnoses.name = ?1")
    List<PatientCard> findAllByDiagnoses_name(String name);

    List<PatientCard> findAll(Sort sort);
}
