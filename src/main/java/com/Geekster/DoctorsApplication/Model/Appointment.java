package com.Geekster.DoctorsApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @EmbeddedId
    private AppointmentKey appointmentKey;

    @ManyToOne
    @JoinColumn(name = "fk_doctorId")
    private Doctor doctor;

    @OneToOne
    private Patient patient;
}
