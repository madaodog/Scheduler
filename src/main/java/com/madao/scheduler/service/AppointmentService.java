package com.madao.scheduler.service;

import com.madao.scheduler.model.Appointment;
import com.madao.scheduler.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
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
}
