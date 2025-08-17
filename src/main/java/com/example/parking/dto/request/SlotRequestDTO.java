package com.example.parking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotRequestDTO {
    @NotBlank(message = "Slot number is required")
    private String slotNumber;

    @NotNull(message = "Floor ID is required")
    private Long floorId;

    @NotNull(message = "Vehicle Rate ID is required")
    private Long vehicleRateId;
}
