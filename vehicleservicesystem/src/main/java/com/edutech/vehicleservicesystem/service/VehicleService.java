package com.edutech.vehicleservicesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.edutech.vehicleservicesystem.entity.User;
import com.edutech.vehicleservicesystem.entity.Vehicle;
import com.edutech.vehicleservicesystem.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(@NonNull Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesByOwner(@NonNull User owner) {
        return vehicleRepository.findByOwner(owner);
    }

    public Optional<Vehicle> getVehicleById(@NonNull Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(@NonNull Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(@NonNull Long id) {
        vehicleRepository.deleteById(id);
    }
}