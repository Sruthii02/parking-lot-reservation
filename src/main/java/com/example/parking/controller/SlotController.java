package com.example.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.parking.dto.request.SlotRequestDTO;
import com.example.parking.dto.response.SlotResponseDTO;
import com.example.parking.service.SlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SlotResponseDTO create(@RequestBody @Valid SlotRequestDTO req) {
        return slotService.createSlot(req);
    }

    @GetMapping
    public List<SlotResponseDTO> getAllSlots() {
        return slotService.getAllSlots();
    }
}
