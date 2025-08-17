package com.example.parking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRateResponseDTO {
    private Long id;
    private String vehicleType;
    private Double ratePerHour;
}
