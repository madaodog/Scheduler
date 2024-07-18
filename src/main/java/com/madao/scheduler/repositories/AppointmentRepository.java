package com.madao.scheduler.repositories;

import com.madao.scheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientID(Long patientID);
    List<Appointment> findByDoctorID(Long doctorID);


}
