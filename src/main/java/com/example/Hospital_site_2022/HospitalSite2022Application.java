package com.example.Hospital_site_2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HospitalSite2022Application {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSite2022Application.class, args);
	}

}
