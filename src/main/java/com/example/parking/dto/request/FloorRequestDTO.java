package com.example.parking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloorRequestDTO {
    @NotBlank(message = "Floor name is required")
    private String name;
}
