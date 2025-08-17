package com.example.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.dto.request.FloorRequestDTO;
import com.example.parking.dto.response.FloorResponseDTO;
import com.example.parking.service.FloorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/floors")
public class FloorController {
    @Autowired
    private FloorService floorService;

    @PostMapping("/create")
    public ResponseEntity<FloorResponseDTO> createFloor(@RequestBody @Valid FloorRequestDTO floor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(floorService.createFloor(floor));
    }

    @GetMapping("/")
    public ResponseEntity<List<FloorResponseDTO>> getAllFloors() {
        return ResponseEntity.ok(floorService.getAllFloors());
    }
}
