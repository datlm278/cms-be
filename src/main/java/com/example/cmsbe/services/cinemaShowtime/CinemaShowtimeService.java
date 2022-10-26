package com.example.cmsbe.services.cinemaShowtime;

import com.example.cmsbe.dto.response.CinemaShowtimeResponse;
import com.example.cmsbe.entity.CinemaShowtime;
import com.example.cmsbe.repositories.CinemaShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CinemaShowtimeService implements ICinemaShowtimeService{

    @Autowired
    private final CinemaShowtimeRepository cinemaShowtimeRepository;

    public CinemaShowtimeService(CinemaShowtimeRepository cinemaShowtimeRepository) {
        this.cinemaShowtimeRepository = cinemaShowtimeRepository;
    }


    @Override
    public CinemaShowtimeResponse createCinemaShowtime(CinemaShowtime request) {
        return null;
    }

    @Override
    public CinemaShowtimeResponse updateCinemaShowtime(CinemaShowtime request) {
        return null;
    }

    @Override
    public void deleteCinemaShowtime(Long id) {

    }

    @Override
    public CinemaShowtimeResponse findCinemaShowtimeById(Long id) {
        return null;
    }

    @Override
    public List<CinemaShowtime> findAllCinemaShowtime() {
        return null;
    }

    @Override
    public List<CinemaShowtime> findShowtimeByCinemaName(String cinemaName) {
        return null;
    }

    @Override
    public List<CinemaShowtime> findCinemaByShowtimeDatetime(String date, String time) {
        return null;
    }
}
