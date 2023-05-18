package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.Model.AuthenticationToken;
import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Model.Patient;
import com.Geekster.DoctorsApplication.Repository.IAuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationTokenRepository tokenRepository;

    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }

    public AuthenticationToken getToken(Patient patient) {
        return tokenRepository.findByPatient(patient);
    }

    public AuthenticationToken getToken(Doctor  doctor) {
        return tokenRepository.findByDoctor(doctor);
    }
    public boolean authenticate(String userEmail, String token) {
        AuthenticationToken authenticationToken = tokenRepository.findFirstByToken(token);
        String  expectedEmail = authenticationToken.getPatient().getPatientEmail();
        return expectedEmail.equals(userEmail);

    }


}
