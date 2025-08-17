package com.example.parking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.parking.dto.request.ReservationRequestDTO;
import com.example.parking.dto.response.ReservationResponseDTO;
import com.example.parking.dto.response.SlotResponseDTO;

public interface ReservationService {
    ReservationResponseDTO reserve(ReservationRequestDTO dto);

    ReservationResponseDTO getReservation(Long id);

    void cancelReservation(Long id);

    // List<SlotResponseDTO> getAvailableSlots(LocalDateTime startTime,
    // LocalDateTime endTime);
    Page<SlotResponseDTO> getAvailableSlots(LocalDateTime startTime, LocalDateTime endTime,
            int page, int size, String sortBy);

    List<ReservationResponseDTO> getAllReservationS();
}
