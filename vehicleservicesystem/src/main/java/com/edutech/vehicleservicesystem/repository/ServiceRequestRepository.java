package com.edutech.vehicleservicesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.entity.User;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByOwner(User owner);
    List<ServiceRequest> findByMechanic(Mechanic mechanic);
}