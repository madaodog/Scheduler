package com.madao.scheduler.controller;

import com.madao.scheduler.model.Appointment;
import com.madao.scheduler.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.madao.scheduler.repositories.AppointmentRepository;

import java.time.LocalTime;
import java.util.List;

@RequestMapping("/api")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentService.addAppointment(appointment), HttpStatus.CREATED);
    }

    @GetMapping("/appointment/{patientID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientID(@PathVariable Long patientID) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientID(patientID), HttpStatus.OK);
    }

    @GetMapping("/appointment/{doctorID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorID(@PathVariable Long doctorID) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDoctorID(doctorID), HttpStatus.OK);
    }

    @PostMapping("/appointment/{availabilityID}")
    public ResponseEntity<Appointment> bookAppointment(@PathVariable Long availabilityID, @RequestBody String email, @RequestBody LocalTime startTime,
                                                       @RequestBody String name, @RequestBody String phoneNumber, @RequestBody String address) {
        return new ResponseEntity<>(appointmentService.bookAppointment(availabilityID, email, startTime, name, phoneNumber, address), HttpStatus.OK);
    }

}
