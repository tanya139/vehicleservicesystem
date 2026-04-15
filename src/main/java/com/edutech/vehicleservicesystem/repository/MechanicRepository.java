package com.edutech.vehicleservicesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edutech.vehicleservicesystem.entity.Mechanic;
import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    Optional<Mechanic> findByName(String name);
}
