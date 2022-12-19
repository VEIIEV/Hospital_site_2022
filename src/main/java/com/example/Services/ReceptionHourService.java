package com.example.Services;

import com.example.DTO.ReceptionHourDTO;
import com.example.Entity.Patient;
import com.example.Repository.PatientRepository;
import com.example.Repository.ReceptionHourRepository;
import com.example.Repository.SpecialisationRepository;
import com.example.Repository.UserRepository;
import com.example.Utils.ReceptionHourMapper;
import com.example.Entity.Doctor;
import com.example.Entity.ReceptionHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReceptionHourService {

    @Autowired
    PatientRepository patientRepository;

    private final ReceptionHourRepository receptionHourRepository;
    private final DoctorService doctorService;
    private final ReceptionHourMapper receptionHourMapper;
    private final SpecialisationRepository specialisationRepository;

    public ReceptionHourService(ReceptionHourRepository receptionHourRepository, DoctorService doctorService, ReceptionHourMapper receptionHourMapper,
                                SpecialisationRepository specialisationRepository) {
        this.receptionHourRepository = receptionHourRepository;
        this.doctorService = doctorService;
        this.receptionHourMapper = receptionHourMapper;
        this.specialisationRepository = specialisationRepository;
    }


    //переделать
    // должно не всем ставить 1
    //а разделять на 1 и 2
    @Scheduled(cron = "* 0/30 8-17 * * 1-5")
    public void outdateReceptionHour() {
        List<ReceptionHour> outdated = receptionHourRepository.findOutdated();
        outdated.stream().forEach(n -> {
                    if (n.getStatus() == 3) {
                        n.setStatus(1);
                    } else if (n.getStatus() == 4) {
                        n.setStatus(2);
                    }
                    receptionHourRepository.save(n);
                }
        );

    }

    //этот запрос найдёт мне часы всех врачей, нужно находить во времени и id доктора

    public ResponseEntity<ReceptionHourDTO> makeAppointment(ReceptionHourDTO receptionHourDTO) {
        try {
            ReceptionHour receptionHour = receptionHourMapper.toReceptionHour(receptionHourDTO);
            ReceptionHour existedReceptionHour = receptionHourRepository.findDistinctTopByDateTimeAndDoctor_IdAndStatus(receptionHour.getDateTime(), receptionHour.getDoctor().getId(), 3).orElseThrow();
            receptionHour.setId(existedReceptionHour.getId());
            receptionHour.setStatus(4);
            receptionHourRepository.save(receptionHour);
            return new ResponseEntity<>(receptionHourMapper.toDTO(receptionHour), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    public ResponseEntity<ReceptionHourDTO> createReceptionHour(ReceptionHourDTO receptionHourDTO) {
        try {
            ReceptionHour receptionHour = receptionHourMapper.toReceptionHour(receptionHourDTO);
            receptionHourRepository.save(receptionHour);
            ReceptionHour createdReceptionHour = receptionHourRepository.findById(receptionHour.getId()).orElseThrow();
            return new ResponseEntity<>(receptionHourMapper.toDTO(createdReceptionHour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> createMonthScheduleFor(Long doctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId).getBody();

        try {
            Long dcid = doctor.getId();
            String str = LocalDateTime.now().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
            for (int i = 1; i < 31; i++) {
                for (int j = 8; j < 16; j++) {
                    localDateTime = localDateTime.withHour(j).withDayOfMonth(i).withMinute(30).withSecond(0).withNano(0);
                    ReceptionHour receptionHour = new ReceptionHour(doctor, localDateTime, 3);
                    //System.out.println(receptionHour.getDoctor().getId());
                    if (receptionHourRepository.findDistinctTopByDateTimeAndDoctor_Id(localDateTime, doctorId).isEmpty()) {
                        receptionHourRepository.save(receptionHour);
                    }
                }
            }
            return new ResponseEntity<>(1, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<ReceptionHourDTO> updateReceptionHour(ReceptionHourDTO receptionHourDTO) {

        if (receptionHourRepository.findById(receptionHourDTO.getId()).isPresent()) {
            try {
                ReceptionHour receptionHour = receptionHourMapper.toReceptionHour(receptionHourDTO);
                receptionHourRepository.save(receptionHour);
                ReceptionHour createdReceptionHour = receptionHourRepository.findById(receptionHour.getId()).orElseThrow();
                return new ResponseEntity<>(receptionHourMapper.toDTO(createdReceptionHour), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> deleteReceptionHour(Long id) {
        if (receptionHourRepository.findById(id).isPresent()) {
            receptionHourRepository.deleteById(id);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ReceptionHourDTO> getReceptionHour(Long id) {
        if (receptionHourRepository.findById(id).isPresent()) {
            ReceptionHour receptionHour = receptionHourRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(receptionHourMapper.toDTO(receptionHour), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ReceptionHourDTO>> getAllReceptionHour(String sortMethod, String title) {

        List<ReceptionHour> receptionHours;
        receptionHours = switch (sortMethod) {
            case "asc" -> receptionHourRepository.findAll(Sort.by(title).ascending());
            case "desc" -> receptionHourRepository.findAll(Sort.by(title).descending());
            default -> receptionHourRepository.findAll();
        };

        List<ReceptionHourDTO> receptionHourDTOS = receptionHours.stream().map(receptionHourMapper::toDTO).toList();
        return new ResponseEntity<>(receptionHourDTOS, HttpStatus.OK);
    }

    public ResponseEntity<List<ReceptionHourDTO>> findOutdated() {
        List<ReceptionHour> receptionHours = receptionHourRepository.findOutdated();

        List<ReceptionHourDTO> receptionHourDTOS = receptionHours.stream().map(receptionHourMapper::toDTO).toList();

        return new ResponseEntity(receptionHourDTOS, HttpStatus.OK);
    }

    //раз в 10 секунд находит устаревшие часы приёмы и меняет их статус
    //сверяется только по датам, есть такая проблемка
    @Scheduled(cron = "*/10 * * * * *")
    public void Outdate() {
        List<ReceptionHour> receptionHours = receptionHourRepository.findOutdated();
        receptionHours.forEach(r -> {
            r.setStatus(r.getStatus() - 2);
            receptionHourRepository.save(r);
        });
    }


    //site

    public List<ReceptionHour> getAllReceptionHourNoApi(Doctor doctor) {
        return receptionHourRepository.findAllByDoctor(doctor);
    }

    public List<ReceptionHour> getAvailableReceptionHourNoApi(Doctor doctor) {
        return  receptionHourRepository.findAvailable(doctor);
    }

    public boolean makeAppointmentNoApi(Long id, String name) {
        try {
            Patient patient = (Patient) patientRepository.findByUsername(name);
            ReceptionHour receptionHour = receptionHourRepository.findById(id).get();
            receptionHour.setStatus(4);
            receptionHour.setPatient(patient);
            receptionHourRepository.save(receptionHour);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }



}


