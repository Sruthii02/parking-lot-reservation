package com.example.parking.service;

import java.util.List;

import com.example.parking.dto.request.SlotRequestDTO;
import com.example.parking.dto.response.SlotResponseDTO;

public interface SlotService {
    SlotResponseDTO createSlot(SlotRequestDTO dto);
    List<SlotResponseDTO> getAllSlots();
}
