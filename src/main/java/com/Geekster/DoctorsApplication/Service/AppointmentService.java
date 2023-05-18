package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.Model.Appointment;
import com.Geekster.DoctorsApplication.Model.AppointmentKey;
import com.Geekster.DoctorsApplication.Repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository appointmentRepository;

    //Save an appointment
    public ResponseEntity<Appointment> bookAppointment(Appointment appointment) {
        return new ResponseEntity<>(appointmentRepository.save(appointment), HttpStatus.OK);
    }

    //cancel appointment
    public void cancelAppointment(AppointmentKey appointmentKey) {
        appointmentRepository.deleteById(appointmentKey);
    }
}
