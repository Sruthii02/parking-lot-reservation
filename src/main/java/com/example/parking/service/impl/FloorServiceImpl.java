package com.example.parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parking.dto.request.FloorRequestDTO;
import com.example.parking.dto.response.FloorResponseDTO;
import com.example.parking.entity.Floor;
import com.example.parking.repository.FloorRepository;
import com.example.parking.service.FloorService;

@Service
public class FloorServiceImpl implements FloorService{

    @Autowired
    private FloorRepository floorRepository;

    @Override
    public FloorResponseDTO createFloor(FloorRequestDTO dto) {
        Floor floor = new Floor();
        floor.setName(dto.getName());
        Floor saved = floorRepository.save(floor);
        return new FloorResponseDTO(saved);
    }

    @Override
    public List<FloorResponseDTO> getAllFloors() {
        return floorRepository.findAll()
                .stream()
                .map(FloorResponseDTO::new)
                .toList();
    }
}
