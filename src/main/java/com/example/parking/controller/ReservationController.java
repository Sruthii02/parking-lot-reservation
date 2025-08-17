package com.example.parking.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.dto.request.ReservationRequestDTO;
import com.example.parking.dto.response.ReservationResponseDTO;
import com.example.parking.dto.response.SlotResponseDTO;
import com.example.parking.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationResponseDTO> getAllSlots() {
        return reservationService.getAllReservationS();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO reserve(@RequestBody @Valid ReservationRequestDTO req) {
        return reservationService.reserve(req);
    }

    @GetMapping("/{id}")
    public ReservationResponseDTO getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("Reservation cancelled successfully");
    }

    // @GetMapping("/availability")
    // public List<SlotResponseDTO> getAvailableSlots(
    // @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // LocalDateTime startTime,
    // @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // LocalDateTime endTime) {
    // return reservationService.getAvailableSlots(startTime, endTime);
    // }

    @GetMapping("/availability")
    public List<SlotResponseDTO> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<SlotResponseDTO> slotsPage = reservationService.getAvailableSlots(startTime, endTime, page, size, sortBy);
        return slotsPage.getContent(); // returns only the list
    }
}