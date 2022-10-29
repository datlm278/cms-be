package com.example.cmsbe.repositories;

import com.example.cmsbe.entity.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {

    @Query(value = "SELECT * FROM CMS_CINEMA_ROOM WHERE ROOM_CODE = :roomCode", nativeQuery = true)
    Optional<CinemaRoom> findByRoomCode(@Param("roomCode") String roomCode);
}
