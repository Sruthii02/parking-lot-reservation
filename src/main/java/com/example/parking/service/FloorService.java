package com.example.parking.service;

import java.util.List;

import com.example.parking.dto.request.FloorRequestDTO;
import com.example.parking.dto.response.FloorResponseDTO;

public interface FloorService {
    FloorResponseDTO createFloor(FloorRequestDTO dto);
    List<FloorResponseDTO> getAllFloors();   
}