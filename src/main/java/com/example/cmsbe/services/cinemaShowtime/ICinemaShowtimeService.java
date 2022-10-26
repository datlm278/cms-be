package com.example.cmsbe.services.cinemaShowtime;

import com.example.cmsbe.dto.response.CinemaShowtimeResponse;
import com.example.cmsbe.entity.CinemaShowtime;

import java.util.List;

public interface ICinemaShowtimeService {

    CinemaShowtimeResponse createCinemaShowtime(CinemaShowtime request);

    CinemaShowtimeResponse updateCinemaShowtime(CinemaShowtime request);

    void deleteCinemaShowtime(Long id);

    CinemaShowtimeResponse findCinemaShowtimeById(Long id);

    List<CinemaShowtime> findAllCinemaShowtime();

    List<CinemaShowtime> findShowtimeByCinemaName(String cinemaName);

    List<CinemaShowtime> findCinemaByShowtimeDatetime(String date, String time);
}
