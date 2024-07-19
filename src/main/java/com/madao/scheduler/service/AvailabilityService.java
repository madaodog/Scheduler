package com.madao.scheduler.service;

import com.madao.scheduler.model.Availability;
import com.madao.scheduler.repositories.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Availability> getAvailability(Long availabilityID) {
        return availabilityRepository.findById(availabilityID);
    }

    public Availability addSlot(Long availabilityID, int slotIndex) {
        Availability availability = availabilityRepository.findById(availabilityID).get();
        availability.addSlot(slotIndex);
        return availabilityRepository.save(availability);
    }
}
