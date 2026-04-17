package com.edutech.vehicleservicesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.entity.Vehicle;
import com.edutech.vehicleservicesystem.repository.MechanicRepository;
import com.edutech.vehicleservicesystem.repository.ServiceRequestRepository;
import com.edutech.vehicleservicesystem.repository.UserRepository;
import com.edutech.vehicleservicesystem.repository.VehicleRepository;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    // Called by OwnerController
    public ServiceRequest createRequest(@NonNull ServiceRequest request, @NonNull Long vehicleId,
            @NonNull String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        request.setOwner(owner);
        request.setVehicle(vehicle);
        request.setStatus("REQUESTED");
        return serviceRequestRepository.save(request);
    }

    // Called by AdminController
    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    // Called by AdminController
    public ServiceRequest assignMechanic(@NonNull Long serviceId, @NonNull Mechanic mechanic) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        request.setMechanic(mechanic);
        request.setStatus("ASSIGNED");
        return serviceRequestRepository.save(request);
    }

    // Called by OwnerController
    public List<ServiceRequest> getOwnerServices(@NonNull String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return serviceRequestRepository.findByOwner(owner);
    }

    // Called by MechanicController
    public List<ServiceRequest> getMechanicServices(@NonNull String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Mechanic mechanic = mechanicRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));
        return serviceRequestRepository.findByMechanic(mechanic);
    }

    // Called by MechanicController
    public ServiceRequest updateStatus(@NonNull Long serviceId, @NonNull String status) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        request.setStatus(status);
        return serviceRequestRepository.save(request);
    }
}