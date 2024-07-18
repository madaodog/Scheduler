package com.madao.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  // Lombok @Data combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @NoArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentID;
    private Long doctorID;
    private Long patientID;
    private String date;
    private String timeSlot;

}
