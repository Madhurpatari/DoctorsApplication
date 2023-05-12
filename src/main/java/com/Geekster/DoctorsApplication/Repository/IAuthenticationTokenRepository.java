package com.Geekster.DoctorsApplication.Repository;

import com.Geekster.DoctorsApplication.Model.AuthenticationToken;
import com.Geekster.DoctorsApplication.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findByPatient(Patient patient);

    AuthenticationToken findFirstByToken(String token);
}
