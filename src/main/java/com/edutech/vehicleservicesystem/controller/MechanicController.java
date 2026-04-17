package com.edutech.vehicleservicesystem.controller;

import com.edutech.vehicleservicesystem.entity.Mechanic;
import com.edutech.vehicleservicesystem.entity.ServiceRequest;
import com.edutech.vehicleservicesystem.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping("/requests")
    public List<ServiceRequest> getRequests(@RequestBody Mechanic mechanic) {
        return serviceRequestService.getRequestsByMechanic(mechanic);
    }
}