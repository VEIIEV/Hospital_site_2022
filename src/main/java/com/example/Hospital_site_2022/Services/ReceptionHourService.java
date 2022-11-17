package com.example.Hospital_site_2022.Services;

import com.example.Hospital_site_2022.Repository.ReceptionHourRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReceptionHourService {
    //я хочу что бы новые данные появлялись в таблице, через каждые полтора часа
    //а старые меняли свой статус на завершенный или невостребованный
    private  final ReceptionHourRepository receptionHourRepository;

    public ReceptionHourService(ReceptionHourRepository receptionHourRepository) {
        this.receptionHourRepository = receptionHourRepository;
    }


    @Scheduled(cron = "0 0/30 8-17 * * 1-5")
    public void outdateReceptionHour(){

        
    }

}
