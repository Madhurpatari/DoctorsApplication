package com.Geekster.DoctorsApplication.Service;

import com.Geekster.DoctorsApplication.Repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepository appointmentRepository;
}
