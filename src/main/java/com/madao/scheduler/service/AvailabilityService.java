package com.madao.scheduler.service;

import com.madao.scheduler.model.Availability;
import com.madao.scheduler.repositories.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {
    AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public List<Availability> getAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability addAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public List<Availability> getAvailabilitiesByDoctorID(Long doctorID) {
        return availabilityRepository.findByDoctorID(doctorID);
    }
}
