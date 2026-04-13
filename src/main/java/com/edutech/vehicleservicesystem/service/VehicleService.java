package com.edutech.vehicleservicesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.entity.Vehicle;
import com.edutech.vehicleservicesystem.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesByOwner(User owner) {
        return vehicleRepository.findByOwner(owner);
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}