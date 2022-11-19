package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.Specialisation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialisationRepository extends CrudRepository<Specialisation, Long> {

    List<Specialisation> findAll(Sort sort);

    @Modifying
    void deleteAllBy();

    List<Specialisation> findAll();

}
