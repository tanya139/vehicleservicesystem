package com.edutech.vehicleservicesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.repository.ServiceRequestRepository;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest createRequest(ServiceRequest request) {
        request.setStatus("REQUESTED");
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    public List<ServiceRequest> getRequestsByOwner(User owner) {
        return serviceRequestRepository.findByOwner(owner);
    }

    public List<ServiceRequest> getRequestsByMechanic(Mechanic mechanic) {
        return serviceRequestRepository.findByMechanic(mechanic);
    }

    public Optional<ServiceRequest> getRequestById(Long id) {
        return serviceRequestRepository.findById(id);
    }

    public ServiceRequest assignMechanic(Long serviceId, Mechanic mechanic) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        request.setMechanic(mechanic);
        request.setStatus("ASSIGNED");
        return serviceRequestRepository.save(request);
    }

    public ServiceRequest updateStatus(Long serviceId, String status) {
        ServiceRequest request = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service request not found"));
        request.setStatus(status);
        return serviceRequestRepository.save(request);
    }
}