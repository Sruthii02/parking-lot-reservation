package com.example.parking.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.parking.entity.Reservation;

@Getter
@Setter
public class ReservationResponseDTO {
    private Long id;
    private String vehicleNumber;
    private String slotNumber;
    private String floorName;
    private String vehicleType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double cost;

    public ReservationResponseDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.vehicleNumber = reservation.getVehicleNumber();
        this.slotNumber = reservation.getSlot().getSlotNumber();
        this.floorName = reservation.getSlot().getFloor().getName();
        this.vehicleType = reservation.getSlot().getVehicleRate().getVehicleType();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.cost = reservation.getCost();
    }
}
