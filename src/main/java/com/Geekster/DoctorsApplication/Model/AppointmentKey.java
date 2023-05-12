package com.Geekster.DoctorsApplication.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class AppointmentKey implements Serializable { //serializable is not needed as such u can use if u want
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long appointmentId;
    public LocalDateTime time;
}
