package com.edutech.vehicleservicesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ServiceRequest createRequest(ServiceRequest request, Long vehicleId, String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        request.setOwner(owner);
        request.setVehicle(vehicle);
        request.setStatus("REQUESTED");
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest assignMechanic(Long serviceId, Long mechanicId) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));
        request.setMechanic(mechanic);
        request.setStatus("ASSIGNED");
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getOwnerServices(String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return serviceRequestRepository.findByOwner(owner);
    }

    public List<ServiceRequest> getMechanicServices(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Mechanic mechanic = mechanicRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));
        return serviceRequestRepository.findByMechanic(mechanic);
    }

    public ServiceRequest updateStatus(Long serviceId, String status) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        request.setStatus(status);
        return serviceRequestRepository.save(request);
    }
}