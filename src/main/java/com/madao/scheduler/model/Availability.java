package com.madao.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long availabilityID;
    private Long doctorID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

}
