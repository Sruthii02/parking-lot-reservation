package com.example.parking.dto.response;

import com.example.parking.entity.Slot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotResponseDTO {
    private Long id;
    private String slotNumber;
    private String floorName;
    private String vehicleType;

    public SlotResponseDTO(Slot slot) {
        this.id = slot.getId();
        this.slotNumber = slot.getSlotNumber();
        this.floorName = slot.getFloor().getName();
        this.vehicleType = slot.getVehicleRate().getVehicleType();
    }
}
