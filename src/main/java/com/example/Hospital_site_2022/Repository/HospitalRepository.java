package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Hospital;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    public Optional<Hospital> findById(Long id);

    @Modifying
    void deleteAllBy();
}
