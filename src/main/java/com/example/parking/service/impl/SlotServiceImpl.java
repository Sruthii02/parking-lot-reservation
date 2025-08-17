package com.example.parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parking.dto.request.SlotRequestDTO;
import com.example.parking.dto.response.SlotResponseDTO;
import com.example.parking.entity.Floor;
import com.example.parking.entity.Slot;
import com.example.parking.entity.VehicleRate;
import com.example.parking.exception.ResourceNotFoundException;
import com.example.parking.repository.FloorRepository;
import com.example.parking.repository.SlotRepository;
import com.example.parking.repository.VehicleRateRepository;
import com.example.parking.service.SlotService;

@Service
public class SlotServiceImpl implements SlotService{
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private VehicleRateRepository vehicleRateRepository;

    @Override
    public SlotResponseDTO createSlot(SlotRequestDTO dto) {
        Floor floor = floorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new ResourceNotFoundException("Floor not found with id " + dto.getFloorId()));

        VehicleRate rate = vehicleRateRepository.findById(dto.getVehicleRateId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle rate not found with id " + dto.getVehicleRateId()));

        Slot slot = new Slot();
        slot.setSlotNumber(dto.getSlotNumber());
        slot.setFloor(floor);
        slot.setVehicleRate(rate);

        Slot saved = slotRepository.save(slot);
        return new SlotResponseDTO(saved);
    }

    @Override
    public List<SlotResponseDTO> getAllSlots() {
        return slotRepository.findAll()
                .stream()
                .map(SlotResponseDTO::new)
                .toList();
    }
}
