package com.example.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parking.entity.Floor;

public interface FloorRepository extends JpaRepository<Floor, Long>{
    
}
