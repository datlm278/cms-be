package com.example.cmsbe.services.cinema;

import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICinemaService {
    CinemaResponse createCinema(MultipartFile[] files, CinemaRequest cinemaRequest) throws IOException;

    CinemaResponse updateCinema(MultipartFile[] files, String cinemaRequest, Long id) throws IOException;

    void deleteCinema(Long id);

    List<CinemaResponse> findAllCinema();

    CinemaResponse findCinemaById(Long id);
}
