package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Doctor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends UserRepository {

    List<Doctor> findAll();
    List<Doctor> findAll(Sort sort);
    Optional<Doctor> findById(Long id);
}
