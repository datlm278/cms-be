package com.example.cmsbe.services.cinemaType;

import com.example.cmsbe.dto.request.CinemaTypeRequest;
import com.example.cmsbe.dto.response.CinemaTypeResponse;

import java.util.List;

public interface ICinemaTypeService {
    CinemaTypeResponse createCinemaType(CinemaTypeRequest cinemaTypeRequest);

    CinemaTypeResponse updateCinemaType(CinemaTypeRequest cinemaTypeRequest, Long id);

    void deleteCinemaType(Long id);

    CinemaTypeResponse findCinemaTypeById(Long id);

    List<CinemaTypeResponse> findAllCinemaType();
}
