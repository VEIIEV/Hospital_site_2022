package com.example.Repository;

import com.example.Entity.Doctor;
import com.example.Entity.Patient;
import com.example.Entity.ReceptionHour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptionHourRepository extends CrudRepository<ReceptionHour, Long> {
    @Transactional
    @Modifying
    @Query("update ReceptionHour r set r.status = 2 where r.dateTime = ?1 and r.patient = ?2")
    int endReceptionhour(LocalDateTime dateTime, Patient patient);
    @Transactional
    @Modifying
    @Query("delete from ReceptionHour r")
    int deleteFirstBy();

    List<ReceptionHour> findAll();
    List<ReceptionHour> findAll(Sort sort);
    @Query("select r from ReceptionHour r where r.doctor = ?1 and r.patient is not null ")
    List<ReceptionHour> findAllByDoctor(Doctor doctor);
    List<ReceptionHour> findAllByDoctor(Doctor doctor, Sort sort);

    @Query("select r from ReceptionHour r where r.patient = ?1")
    List<ReceptionHour> findAllByPatient(Patient patient);

    @Query("select r from ReceptionHour r where r.patient = ?1 and r.status = ?2")
    List<ReceptionHour> findAllByPatientAndStatus(Patient patient, int status);

    Optional<ReceptionHour> findDistinctTopByDateTimeAndDoctor_IdAndStatus(LocalDateTime localDateTime, Long doctor_id, int status);
    Optional<ReceptionHour> findDistinctTopByDateTimeAndDoctor_Id(LocalDateTime localDateTime, Long doctor_id);

    Optional<ReceptionHour> findById(Long id);


    @Modifying
    void deleteAllBy();

    @Query(value = " select * from hospital.reception_hour where  (status=3 or  status = 4 ) and reception_hour.date_time < current_date;", nativeQuery = true)
    List<ReceptionHour> findOutdated();

    @Query(" select r from ReceptionHour r where  r.status=3 and r.doctor=?1")
    List<ReceptionHour> findAvailable(Doctor doctor);

    @Query("select r from ReceptionHour r where r.status=4 and r.doctor=?1")
    List<ReceptionHour> findAppointed(Doctor doctor);


    @Query("select r from ReceptionHour r where r.doctor = ?1 and r.status = ?2")
    List<ReceptionHour> findAllByDoctorAndStatus(Doctor doctor, int i);
}
