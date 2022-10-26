package com.example.cmsbe.repositories;

import com.example.cmsbe.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Query(value = "SELECT * FROM CMS_SHOWTIME CS WHERE CS.DAY = :date AND CS.HOUR = :time", nativeQuery = true)
    Showtime findShowtimeByDatetime(@Param("date") String date, @Param("time")String time);

    @Query(value = "SELECT * FROM CMS_SHOWTIME CS WHERE CS.DAY = :date", nativeQuery = true)
    List<Showtime> findShowtimeByDate(@Param("date") String date);
}
