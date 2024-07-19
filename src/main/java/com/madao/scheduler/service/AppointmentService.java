package com.madao.scheduler.service;

import com.madao.scheduler.model.Appointment;
import com.madao.scheduler.model.Availability;
import com.madao.scheduler.model.Patient;
import com.madao.scheduler.repositories.AppointmentRepository;
import com.madao.scheduler.repositories.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;
    AvailabilityService availabilityService;

    PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, AvailabilityService availabilityService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.availabilityService = availabilityService;
        this.patientService = patientService;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatientID(Long patientID) {
        return appointmentRepository.findByPatientID(patientID);
    }

    public List<Appointment> getAppointmentsByDoctorID(Long doctorID) {
        return appointmentRepository.findByDoctorID(doctorID);
    }

    public Appointment bookAppointment(Long availabilityID, String email, LocalTime startTime, String name, String phoneNumber, String address) {
        Availability availability = availabilityService.getAvailability(availabilityID).get();
        int slotIndex = (startTime.getHour() * 60 + startTime.getMinute()) / 30;
        if(isAvailable(availabilityID, startTime)) {
            if(patientService.isPatient(email)) {
                Long patientID = patientService.getPatient(email).getPatientID();
                availabilityService.addSlot(availabilityID, slotIndex);
                return appointmentRepository.save(new Appointment(availability.getDoctorID(), patientID, availability.getDate(), startTime, startTime.plusMinutes(30)));

            } else {
                // Create a new patient
                patientService.addPatient(new Patient(name, phoneNumber, email, address));
                Long patientID = patientService.getPatient(email).getPatientID();
                availabilityService.addSlot(availabilityID, slotIndex);
                return appointmentRepository.save(new Appointment(availability.getDoctorID(), patientID, availability.getDate(), startTime, startTime.plusMinutes(30)));
            }
        } else {
            throw new IllegalArgumentException("Appointment slot is not available");
        }
        }


    /**
     * Check firstly if the startTime is within the availability time frame
     * Then check if the startTime is a multiple of 30 minutes
     * Lastly check if the appointment slot is available (and not already booked)
     */
    public boolean isAvailable(Long availabilityID, LocalTime startTime) {
        Optional<Availability> availability = availabilityService.getAvailability(availabilityID);
        if (availability.isPresent()) {
            int minutes = startTime.getHour() * 60 + startTime.getMinute();
            int index = minutes / 30;
            if ((startTime.equals(availability.get().getStartTime()) || startTime.isAfter(availability.get().getStartTime()))
                    && (startTime.equals(availability.get().getEndTime()) || startTime.isBefore(availability.get().getEndTime()))
                    && minutes % 30 == 0 && availability.get().getBookedAppointments().get(index) == 0) {
                return true;
            }
        }
        return false;
    }

}
