package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.DTO.ReceptionHourDTO;
import com.example.Hospital_site_2022.Entity.Doctor;
import com.example.Hospital_site_2022.Entity.ReceptionHour;
import com.example.Hospital_site_2022.Repository.ReceptionHourRepository;
import com.example.Hospital_site_2022.Utils.ReceptionHourMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionHourService {

    private final ReceptionHourRepository receptionHourRepository;
    private final DoctorService doctorService;
    private final ReceptionHourMapper receptionHourMapper;

    public ReceptionHourService(ReceptionHourRepository receptionHourRepository, DoctorService doctorService, ReceptionHourMapper receptionHourMapper) {
        this.receptionHourRepository = receptionHourRepository;
        this.doctorService = doctorService;
        this.receptionHourMapper = receptionHourMapper;
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

    public ResponseEntity<ReceptionHourDTO> makeAppointment(ReceptionHourDTO receptionHourDTO){
        try {
            ReceptionHour receptionHour = receptionHourMapper.toReceptionHour(receptionHourDTO);
            ReceptionHour existedReceptionHour = receptionHourRepository.findByDateTime(receptionHour.getDateTime()).orElseThrow();
            receptionHour.setId(existedReceptionHour.getId());
            receptionHour.setStatus(4);
            receptionHourRepository.save(receptionHour);
            return new ResponseEntity<>(receptionHourMapper.toDTO(receptionHour), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
        String str = LocalDateTime.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        for (int i = 1; i < 31; i++) {
            for (int j = 8; j < 16; j++) {
                localDateTime = localDateTime.withHour(j).withDayOfMonth(i).withMinute(30).withSecond(0).withNano(0);
                ReceptionHour receptionHour = new ReceptionHour(doctor, localDateTime, 3);
                receptionHourRepository.save(receptionHour);
            }
        }
        return new ResponseEntity<>(1, HttpStatus.OK);
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
}
