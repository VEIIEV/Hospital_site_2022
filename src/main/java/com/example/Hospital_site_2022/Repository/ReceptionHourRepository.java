package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.ReceptionHour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionHourRepository extends CrudRepository<ReceptionHour, Long> {

    List<ReceptionHour> findAll();
    List<ReceptionHour> findAll(Sort sort);

    Optional<ReceptionHour> findByDateTime(LocalDateTime localDateTime);

    Optional<ReceptionHour> findById(Long id);





    @Modifying
    void deleteAllBy();

    @Query(value = " select * from hospital.reception_hour where  (status=3 or  status = 4 ) and reception_hour.date_time < current_date;", nativeQuery = true)
    List<ReceptionHour> findOutdated();




}
