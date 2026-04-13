package com.edutech.vehicleservicesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.vehicleservicesystem.entity.Parts;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Long> {
}