package com.madao.scheduler.controller;

import com.madao.scheduler.model.Availability;
import com.madao.scheduler.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AvailabilityController {

    AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Availability>> getAvailabilities() {
        return new ResponseEntity<>(availabilityService.getAvailabilities(), HttpStatus.OK);
    }

    @GetMapping("/availability/{doctorID}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByDoctorID(@PathVariable Long doctorID) {
        return new ResponseEntity<>(availabilityService.getAvailabilitiesByDoctorID(doctorID), HttpStatus.OK);
    }

    @PostMapping("/availability")
    public ResponseEntity<Availability> addAvailability(@RequestBody Availability availability) {
        return new ResponseEntity<>(availabilityService.addAvailability(availability), HttpStatus.CREATED);
    }
}
