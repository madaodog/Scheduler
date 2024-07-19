package com.madao.scheduler.service;

import com.madao.scheduler.model.Patient;
import org.springframework.stereotype.Service;
import com.madao.scheduler.repositories.PatientRepository;

@Service
public class PatientService {

    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean isPatient(String email) {
        return patientRepository.findByEmail(email).isPresent();
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatient(String email) {
        return patientRepository.findByEmail(email).get();
    }

}
