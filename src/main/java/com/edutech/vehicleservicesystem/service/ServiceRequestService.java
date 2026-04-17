package com.edutech.vehicleservicesystem.service;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest saveRequest(ServiceRequest request) {
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getRequestsByMechanic(Mechanic mechanic) {
        return serviceRequestRepository.findByMechanic(mechanic);
    }

    public List<ServiceRequest> getRequestsByOwner(User owner) {
        return serviceRequestRepository.findByOwner(owner);
    }

    public ServiceRequest assignMechanic(Long requestId, Mechanic mechanic) {
        Optional<ServiceRequest> optional = serviceRequestRepository.findById(requestId);
        if (optional.isPresent()) {
            ServiceRequest request = optional.get();
            request.setMechanic(mechanic);
            return serviceRequestRepository.save(request);
        }
        return null;
    }

    public ServiceRequest updateStatus(Long requestId, String status) {
        Optional<ServiceRequest> optional = serviceRequestRepository.findById(requestId);
        if (optional.isPresent()) {
            ServiceRequest request = optional.get();
            request.setStatus(status);
            return serviceRequestRepository.save(request);
        }
        return null;
    }

    public ServiceRequest createRequest(ServiceRequest request, Long vehicleId, String ownerEmail) {
        return serviceRequestRepository.save(request);
    }

    public List<ServiceRequest> getOwnerServices(String email) {
        return serviceRequestRepository.findAll();
    }
}