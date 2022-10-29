package com.example.cmsbe.repositories;

import com.example.cmsbe.entity.SeatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRoomRepository extends JpaRepository<SeatRoom, Long> {

    @Query(value = "SELECT * FROM CMS_SEAT_ROOM WHERE CINEMA_ROOM_CODE = :roomCode", nativeQuery = true)
    List<SeatRoom> findSeatByCinemaRoomCode(String roomCode);
}
