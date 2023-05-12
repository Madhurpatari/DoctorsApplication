package com.Geekster.DoctorsApplication.Repository;

import com.Geekster.DoctorsApplication.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Patient findFirstByPatientEmail(String userEmail);
}
