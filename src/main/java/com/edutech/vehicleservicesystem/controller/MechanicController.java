package com.edutech.vehicleservicesystem.controller;

import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanic")
@CrossOrigin(origins = "*")
public class MechanicController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    // GET /api/mechanic/services?username=john
    @GetMapping("/services")
    public ResponseEntity<List<ServiceRequest>> getAssignedServices(@RequestParam String username) {
        return ResponseEntity.ok(serviceRequestService.getMechanicServices(username));
    }

    // PUT /api/mechanic/update/{serviceId}?status=IN_PROGRESS
    @PutMapping("/update/{serviceId}")
    public ResponseEntity<?> updateServiceStatus(@PathVariable Long serviceId,
                                                 @RequestParam String status) {
        ServiceRequest updated = serviceRequestService.updateStatus(serviceId, status);
        return ResponseEntity.ok(updated);
    }
}