package com.example.parking.service;

import java.util.List;

import com.example.parking.dto.request.VehicleRateCreateDTO;
import com.example.parking.dto.response.VehicleRateResponseDTO;
import com.example.parking.dto.update.VehicleRateUpdateDTO;

public interface VehicleRateService {
    VehicleRateResponseDTO addVehicleRate(VehicleRateCreateDTO dto);

    VehicleRateResponseDTO updateRate(Long id, VehicleRateUpdateDTO dto);

    List<VehicleRateResponseDTO> getAllRates();
}
