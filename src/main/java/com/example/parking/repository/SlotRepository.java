package com.example.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parking.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {

}
