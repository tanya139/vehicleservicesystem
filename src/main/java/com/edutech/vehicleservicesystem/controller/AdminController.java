package com.edutech.vehicleservicesystem.controller;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.Parts;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.service.MechanicService;
import com.edutech.vehicleservicesystem.service.PartsService;
import com.edutech.vehicleservicesystem.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private MechanicService mechanicService;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private PartsService partsService;

    // POST /api/admin/mechanic
    @PostMapping("/mechanic")
    public ResponseEntity<?> addMechanic(@RequestBody Mechanic mechanic) {
        Mechanic saved = mechanicService.addMechanic(mechanic);
        return ResponseEntity.ok(saved);
    }

    // GET /api/admin/requests
    @GetMapping("/requests")
    public ResponseEntity<List<ServiceRequest>> getAllRequests() {
        return ResponseEntity.ok(serviceRequestService.getAllRequests());
    }

    // PUT /api/admin/assign/{serviceId}?mechanicId=1
    @PutMapping("/assign/{serviceId}")
    public ResponseEntity<?> assignMechanic(@PathVariable Long serviceId,
            @RequestParam Long mechanicId) {
        ServiceRequest updated = serviceRequestService.assignMechanic(serviceId, mechanicId);
        return ResponseEntity.ok(updated);
    }

    // POST /api/admin/parts
    @PostMapping("/parts")
    public ResponseEntity<?> addParts(@RequestBody Parts parts) {
        Parts saved = partsService.addParts(parts);
        return ResponseEntity.ok(saved);
    }
}