package com.madao.scheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Entity
@NoArgsConstructor
@Data
/**
 * First assumption is that a doctor will have one interval of availability per day
 * Second assumption is that a doctor can only have appointments of 30 minutes, nothing more or less
 * Third assumption is that a doctor can only have one appointment per time slot
 * Lastly an extra field will keep track of the appointments that are booked, by keeping a list of integers
 * So for instance, the startTime is 9:00 and the endTime is 17:00, the list will have 32 elements, each element will be 0
 */
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long availabilityID;
    private Long doctorID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @ElementCollection
    private List<Integer> bookedAppointments;

    public Availability(Long doctorID, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.doctorID = doctorID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedAppointments = initializeBookedAppointments();
    }

    private List<Integer> initializeBookedAppointments() {
        long totalMinutes =  ChronoUnit.MINUTES.between(startTime, endTime);
        return new ArrayList<>(Collections.nCopies((int) totalMinutes / 30, 0));
    }

    public void addSlot(int slotIndex) {
        bookedAppointments.set(slotIndex, 1);
    }

}
