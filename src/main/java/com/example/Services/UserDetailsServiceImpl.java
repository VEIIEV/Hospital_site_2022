package com.example.Services;

import com.example.Entity.Doctor;
import com.example.Entity.Patient;
import com.example.Entity.User;
import com.example.Entity.VerificationToken;
import com.example.Repository.*;
import com.example.enums.UserRole;
import com.example.mail.AccountVerificationEmailContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private EmailService emailService;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        return user;
    }



    public <T extends User> User saveUser(User user) throws IllegalArgumentException {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null || user.getUserRole()==null) {
            System.out.println("ex");
            throw new IllegalArgumentException();
        }
        try {
            //почему с 1, получаю ошибку  No enum constant com.example.enums.UserRole.PATIENT
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (user.getUserRole() == UserRole.ROLE_PATIENT) {
                Patient patient = new Patient(user);
                //закрепляем пользователя за больницей №1
                patient.setHospital(hospitalRepository.findById(1L).get());
                Patient patientFromDB =patientRepository.save(patient);
                //вручнаю задать больницу №2
//                patientRepository.updateHospital(patientFromDB.getId());
                System.out.println("check p");
                sendRegistrationConfirmationEmail((User) patientFromDB);
                return patient;
            }
            if (user.getUserRole() == UserRole.ROLE_DOCTOR) {
                Doctor doctor = new Doctor(user);
                Doctor doctorFromDB=doctorRepository.save(doctor);
                System.out.println("check d ");
                sendRegistrationConfirmationEmail( (User) doctorFromDB);
                return doctor;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public void sendRegistrationConfirmationEmail(User user) {
        VerificationToken secureToken= verificationTokenService.createSecureToken();
        secureToken.setUser(user);
        verificationTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void updateImg(MultipartFile multipartImage, Principal principal) throws IOException {

        User user= userRepository.findByUsername(principal.getName());
       // user.setImage(multipartImage.getBytes());
        user.setImage(Base64.getEncoder().encode(multipartImage.getBytes()));

        System.out.println(user.getImage().length);
        userRepository.save(user);

    }


    public boolean verifyUser(String token) throws Exception {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (Objects.isNull(verificationToken) || !StringUtils.equals(token, verificationToken.getToken()) || verificationToken.isExpired()) {
            throw new Exception("Token is not valid");
        }
        User user = (User) userRepository.findById(verificationToken.getUser().getId()).get();
        if (Objects.isNull(user)) {
            return false;
        }
        user.setAccountVerified(true);
        userRepository.save(user); // let’s same user details

        // we don’t need invalid password now
        verificationTokenService.removeToken(verificationToken);
        return true;
    }
}
