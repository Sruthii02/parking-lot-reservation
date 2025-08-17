package com.example.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parking.entity.VehicleRate;

public interface VehicleRateRepository extends JpaRepository<VehicleRate, Long> {

    boolean existsByVehicleType(String vehicleType);

}
