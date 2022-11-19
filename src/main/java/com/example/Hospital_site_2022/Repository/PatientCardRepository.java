package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.PatientCard;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, Long> {

    Optional<PatientCard> findById(Long id );

    List<PatientCard> findAll();

    @Query("select p from PatientCard p inner join p.diagnoses diagnoses where diagnoses.name = ?1")
    List<PatientCard> findAllByDiagnoses_name(String name);

    @Modifying
    void deleteAllBy();

    List<PatientCard> findAll(Sort sort);

    @Query("select p from PatientCard p where p.doctorId = ?1")
    Set<PatientCard> findAllByDoctorId(Long doctorId);

    @Query("select p from PatientCard p where p.patient.id = ?1")
    Set<PatientCard> findAllByPatientId(Long patientId);



}
