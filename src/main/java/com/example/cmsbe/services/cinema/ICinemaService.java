package com.example.cmsbe.services.cinema;

import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICinemaService {
    CinemaResponse createCinema(CinemaRequest cinemaRequest);

    CinemaResponse updateCinema(MultipartFile file, String cinemaRequest, Long id) throws IOException;

    CinemaResponse insertCinema(MultipartFile file, String cinemaRequest) throws IOException;

    void deleteCinema(Long id);

    List<CinemaResponse> findAllCinema();

    CinemaResponse findCinemaById(Long id);
}
