package com.madao.scheduler;
import com.madao.scheduler.model.Appointment;
import com.madao.scheduler.model.Availability;
import com.madao.scheduler.model.Doctor;
import com.madao.scheduler.model.Patient;
import com.madao.scheduler.repositories.AppointmentRepository;
import com.madao.scheduler.repositories.AvailabilityRepository;
import com.madao.scheduler.repositories.DoctorRepository;
import com.madao.scheduler.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataInitializer implements CommandLineRunner {

    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    AppointmentRepository appointmentRepository;

    AvailabilityRepository availabilityRepository;

    public DataInitializer(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, AvailabilityRepository availabilityRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add initial data for Patients
        Patient patient1 = new Patient("Alice Johnson", "123-456-7890", "alice.johnson@example.com", "123 Maple St");
        Patient patient2 = new Patient("Bob Smith", "987-654-3210", "bob.smith@example.com", "456 Oak St");
        patientRepository.save(patient1);
        patientRepository.save(patient2);

        // Add initial data for Doctors
        Doctor doctor1 = new Doctor("Dr. John Smith", "123-456-7890", "john.smith@example.com");
        Doctor doctor2 = new Doctor("Dr. Emily Johnson", "987-654-3210", "emily.johnson@example.com");
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        // Add initial data for Availability
        availabilityRepository.save(new Availability(doctor1.getDoctorID(), LocalDate.of(2024, 7, 18), LocalTime.of(9, 0), LocalTime.of(17, 0)));
        availabilityRepository.save(new Availability(doctor2.getDoctorID(), LocalDate.of(2024, 7, 19), LocalTime.of(10, 0), LocalTime.of(16, 0)));

        // Add initial data for Appointments
        appointmentRepository.save(new Appointment(doctor1.getDoctorID(), patient1.getPatientID(), LocalDate.of(2024, 7, 18), LocalTime.of(10, 0), LocalTime.of(10, 30)));
        appointmentRepository.save(new Appointment(doctor2.getDoctorID(), patient2.getPatientID(), LocalDate.of(2024, 7, 19), LocalTime.of(11, 0), LocalTime.of(11, 30)));
    }

}
