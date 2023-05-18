package com.Geekster.DoctorsApplication.Repository;

import com.Geekster.DoctorsApplication.Model.AuthenticationToken;
import com.Geekster.DoctorsApplication.Model.Doctor;
import com.Geekster.DoctorsApplication.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    //figuring out the patient token
    AuthenticationToken findByPatient(Patient patient);

    AuthenticationToken findFirstByToken(String token);

    //figuring out the doctor token
    AuthenticationToken findByDoctor(Doctor doctor);
}
