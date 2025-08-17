package com.example.parking.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRateUpdateDTO {
    @NotNull(message = "Rate per hour is required")
    @Positive(message = "Rate per hour must be positive")
    private Double newRate;
}
