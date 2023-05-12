package com.Geekster.DoctorsApplication.Repository;

import com.Geekster.DoctorsApplication.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}
