package com.example.cmsbe.repositories;

import com.example.cmsbe.entity.CinemaShowtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaShowtimeRepository extends JpaRepository<CinemaShowtime, Long> {
}
