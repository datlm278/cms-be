package com.example.cmsbe.repositories;

import com.example.cmsbe.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Query(value = "SELECT * FROM CMS_SHOWTIME CS WHERE CS.DAY = :date AND CS.HOUR = :time", nativeQuery = true)
    Showtime findShowtimeByDatetime(@Param("date") String date, @Param("time")String time);

    @Query(value = "SELECT * FROM CMS_SHOWTIME CS WHERE CS.DAY = :date", nativeQuery = true)
    List<Showtime> findShowtimeByDate(@Param("date") String date);

    @Query(value = "SELECT * FROM CMS_SHOWTIME CS WHERE CS.HOUR = :time", nativeQuery = true)
    List<Showtime> findShowtimeByTime(@Param("time") String time);
}
