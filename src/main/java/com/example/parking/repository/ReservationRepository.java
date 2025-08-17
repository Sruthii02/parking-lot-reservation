package com.example.parking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.parking.entity.Reservation;
import com.example.parking.entity.Slot;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

       @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
                     "FROM Reservation r " +
                     "WHERE r.slot.id = :slotId " +
                     "AND ( (r.startTime < :endTime) AND (r.endTime > :startTime) )")
       boolean existsBySlotIdAndTimeRange(@Param("slotId") Long slotId,
                     @Param("startTime") LocalDateTime startTime,
                     @Param("endTime") LocalDateTime endTime);

       // @Query("SELECT DISTINCT r.slot FROM Reservation r " +
       // "WHERE (r.startTime < :endTime) AND (r.endTime > :startTime)")
       // List<Slot> findReservedSlotsInRange(@Param("startTime") LocalDateTime
       // startTime,
       // @Param("endTime") LocalDateTime endTime);

       @Query("SELECT s FROM Slot s WHERE s NOT IN " +
                     "(SELECT r.slot FROM Reservation r WHERE r.startTime < :endTime AND r.endTime > :startTime)")
       Page<Slot> findAvailableSlotsInRange(@Param("startTime") LocalDateTime startTime,
                     @Param("endTime") LocalDateTime endTime,
                     Pageable pageable);
}
