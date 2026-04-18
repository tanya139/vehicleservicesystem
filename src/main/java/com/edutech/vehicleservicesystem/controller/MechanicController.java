package com.edutech.vehicleservicesystem.controller;

import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/mechanic")
@CrossOrigin(origins = "*")
public class MechanicController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceRequest>> getAssignedServices(Authentication authentication) {
        String username = authentication.getName();
        List<ServiceRequest> services = serviceRequestService.getMechanicServices(username);
        return ResponseEntity.ok(services);
    }

    @PutMapping("/update/{serviceId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long serviceId,
            @RequestParam String status) {
        ServiceRequest updated = serviceRequestService.updateStatus(serviceId, status);
        return ResponseEntity.ok(updated);
    }
}