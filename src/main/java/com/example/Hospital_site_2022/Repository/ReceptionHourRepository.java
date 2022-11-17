package com.example.Hospital_site_2022.Repository;

import com.example.Hospital_site_2022.Entity.ReceptionHour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionHourRepository extends CrudRepository<ReceptionHour, Long> {

    List<ReceptionHour> findAll();
    List<ReceptionHour> findAll(Sort sort);

    @Query(value = "select * " +
            "from reception_hour " +
            "where  status=3 or status = 4 " +
            "group by date_time " +
            "having min(date_time)", nativeQuery = true)
    Optional<ReceptionHour> findMinDate();

}
