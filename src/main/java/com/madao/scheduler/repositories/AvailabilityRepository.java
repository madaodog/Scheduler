package com.madao.scheduler.repositories;

import com.madao.scheduler.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findAll();

    List<Availability> findByDoctorID(Long doctorID);

    Optional<Availability> findById(Long id);
}
