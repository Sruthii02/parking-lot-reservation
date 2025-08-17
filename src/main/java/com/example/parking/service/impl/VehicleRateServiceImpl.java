package com.example.parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parking.dto.request.VehicleRateCreateDTO;
import com.example.parking.dto.response.VehicleRateResponseDTO;
import com.example.parking.dto.update.VehicleRateUpdateDTO;
import com.example.parking.entity.VehicleRate;
import com.example.parking.repository.VehicleRateRepository;
import com.example.parking.service.VehicleRateService;

@Service
public class VehicleRateServiceImpl implements VehicleRateService {
    @Autowired
    private VehicleRateRepository vehicleRateRepository;

    public VehicleRateResponseDTO addVehicleRate(VehicleRateCreateDTO dto) {
        if (vehicleRateRepository.existsByVehicleType(dto.getVehicleType())) {
            throw new IllegalArgumentException("Vehicle type already exists: " + dto.getVehicleType());
        }

        VehicleRate vehicleRate = new VehicleRate();
        vehicleRate.setVehicleType(dto.getVehicleType());
        vehicleRate.setRatePerHour(dto.getRatePerHour());

        VehicleRate saved = vehicleRateRepository.save(vehicleRate);

        return new VehicleRateResponseDTO(
                saved.getId(),
                saved.getVehicleType(),
                saved.getRatePerHour());
    }

    public VehicleRateResponseDTO updateRate(Long id, VehicleRateUpdateDTO dto) {
        VehicleRate vehicleRate = vehicleRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle type not found with id: " + id));

        vehicleRate.setRatePerHour(dto.getNewRate());
        VehicleRate updated = vehicleRateRepository.save(vehicleRate);

        return new VehicleRateResponseDTO(
                updated.getId(),
                updated.getVehicleType(),
                updated.getRatePerHour());
    }

    public List<VehicleRateResponseDTO> getAllRates() {
        return vehicleRateRepository.findAll()
                .stream()
                .map(vehicleRate -> new VehicleRateResponseDTO(
                        vehicleRate.getId(),
                        vehicleRate.getVehicleType(),
                        vehicleRate.getRatePerHour()))
                .toList();
    }
}
