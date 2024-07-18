package com.madao.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentID;
    private Long doctorID;
    private Long patientID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    public Appointment(Long doctorID, Long patientID, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
