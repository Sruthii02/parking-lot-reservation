package com.example.parking.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.parking.dto.request.ReservationRequestDTO;
import com.example.parking.dto.response.ReservationResponseDTO;
import com.example.parking.dto.response.SlotResponseDTO;
import com.example.parking.entity.Reservation;
import com.example.parking.entity.Slot;
import com.example.parking.exception.BadRequestException;
import com.example.parking.exception.ResourceNotFoundException;
import com.example.parking.repository.ReservationRepository;
import com.example.parking.repository.SlotRepository;
import com.example.parking.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SlotRepository slotRepository;

    @Override
    public List<ReservationResponseDTO> getAllReservationS() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationResponseDTO::new)
                .toList();
    }

    @Override
    public ReservationResponseDTO reserve(ReservationRequestDTO dto) {
        Slot slot = slotRepository.findById(dto.getSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found with id " + dto.getSlotId()));

        if (dto.getStartTime().isAfter(dto.getEndTime()) || dto.getStartTime().isEqual(dto.getEndTime())) {
            throw new BadRequestException("Start time must be before end time");
        }

        if (Duration.between(dto.getStartTime(), dto.getEndTime()).toHours() > 24) {
            throw new BadRequestException("Reservation cannot exceed 24 hours");
        }

        boolean conflict = reservationRepository.existsBySlotIdAndTimeRange(dto.getSlotId(),
                dto.getStartTime(), dto.getEndTime());
        if (conflict) {
            throw new BadRequestException("Slot already reserved in the given time range");
        }

        double ratePerHour = slot.getVehicleRate().getRatePerHour();
        long hours = (long) Math.ceil(Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes() / 60.0);
        double cost = hours * ratePerHour;

        Reservation reservation = new Reservation();
        reservation.setSlot(slot);
        reservation.setVehicleNumber(dto.getVehicleNumber());
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setCost(cost);

        Reservation saved = reservationRepository.save(reservation);
        return new ReservationResponseDTO(saved);
    }

    @Override
    public ReservationResponseDTO getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
        return new ReservationResponseDTO(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
        reservationRepository.delete(reservation);
    }

    // @Override
    // public List<SlotResponseDTO> getAvailableSlots(LocalDateTime startTime,
    // LocalDateTime endTime) {
    // if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
    // throw new BadRequestException("Start time must be before end time");
    // }

    // List<Slot> allSlots = slotRepository.findAll();
    // List<Slot> reservedSlots =
    // reservationRepository.findReservedSlotsInRange(startTime, endTime);

    // return allSlots.stream()
    // .filter(slot -> !reservedSlots.contains(slot))
    // .map(SlotResponseDTO::new)
    // .toList();
    // }

    @Override
    public Page<SlotResponseDTO> getAvailableSlots(LocalDateTime startTime, LocalDateTime endTime,
            int page, int size, String sortBy) {
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Slot> slotsPage = reservationRepository.findAvailableSlotsInRange(startTime, endTime, pageable);

        List<SlotResponseDTO> dtoList = slotsPage.stream()
                .map(SlotResponseDTO::new)
                .toList();

        return new PageImpl<>(dtoList, pageable, slotsPage.getTotalElements());
    }

}
