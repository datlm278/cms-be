package com.example.cmsbe.services.cinema;

import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;

import java.util.List;

public interface ICinemaService {
    CinemaResponse createCinema(CinemaRequest cinemaRequest);

    CinemaResponse updateCinema(CinemaRequest cinemaRequest, Long id);

    void deleteCinema(Long id);

    List<CinemaResponse> findAllCinema();

    CinemaResponse findCinemaById(Long id);
}
