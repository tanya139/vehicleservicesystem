package com.edutech.vehicleservicesystem.controller;

import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.entity.Vehicle;
import com.edutech.vehicleservicesystem.repository.UserRepository;
import com.edutech.vehicleservicesystem.service.ServiceRequestService;
import com.edutech.vehicleservicesystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "*")
public class OwnerController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private UserRepository userRepository;

    // POST /api/owner/vehicle
    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle,
            Authentication authentication) {
        String username = authentication.getName();
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        vehicle.setOwner(owner);
        Vehicle saved = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(saved);
    }

    // POST /api/owner/service
    @PostMapping("/service")
    public ResponseEntity<?> requestService(@RequestBody ServiceRequest request,
            @RequestParam Long vehicleId,
            Authentication authentication) {
        String username = authentication.getName();
        ServiceRequest saved = serviceRequestService.createRequest(request, vehicleId, username);
        return ResponseEntity.ok(saved);
    }

    // GET /api/owner/services
    @GetMapping("/services")
    public ResponseEntity<List<ServiceRequest>> getServiceHistory(Authentication authentication) {
        String username = authentication.getName();
        List<ServiceRequest> history = serviceRequestService.getOwnerServices(username);
        return ResponseEntity.ok(history);
    }
}