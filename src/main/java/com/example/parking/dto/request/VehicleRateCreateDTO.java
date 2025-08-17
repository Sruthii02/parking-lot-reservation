package com.example.parking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRateCreateDTO {

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotNull(message = "Rate per hour is required")
    @Positive(message = "Rate per hour must be positive")
    private Double ratePerHour;
}
