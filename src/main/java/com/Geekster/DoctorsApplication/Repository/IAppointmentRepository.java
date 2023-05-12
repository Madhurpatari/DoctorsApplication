package com.Geekster.DoctorsApplication.Repository;

import com.Geekster.DoctorsApplication.Model.Appointment;
import com.Geekster.DoctorsApplication.Model.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, AppointmentKey> {
}
