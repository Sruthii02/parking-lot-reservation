package com.example.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.dto.request.VehicleRateCreateDTO;
import com.example.parking.dto.response.VehicleRateResponseDTO;
import com.example.parking.dto.update.VehicleRateUpdateDTO;
import com.example.parking.service.VehicleRateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicleRates")
public class VehicleRateController {
    @Autowired
    private VehicleRateService vehicleRateService;

    @PostMapping
    public ResponseEntity<VehicleRateResponseDTO> addVehicleRate(
            @Valid @RequestBody VehicleRateCreateDTO dto) {
        VehicleRateResponseDTO created = vehicleRateService.addVehicleRate(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleRateResponseDTO> updateRate(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRateUpdateDTO dto) {
        VehicleRateResponseDTO updated = vehicleRateService.updateRate(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<VehicleRateResponseDTO>> getAllRates() {
        List<VehicleRateResponseDTO> rates = vehicleRateService.getAllRates();
        return ResponseEntity.ok(rates);
    }
}
