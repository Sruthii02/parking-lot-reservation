package com.example.parking.dto.response;

import com.example.parking.entity.Floor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloorResponseDTO {
    private Long id;
    private String name;

    public FloorResponseDTO(Floor floor) {
        this.id = floor.getId();
        this.name = floor.getName();
    }
}
