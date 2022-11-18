package com.example.cmsbe.mapper;

import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.example.cmsbe.entity.Cinema;

public class CinemaMapper {

    public static CinemaResponse toDto(Cinema cinema) {
        CinemaResponse cinemaResponse = new CinemaResponse();
        cinemaResponse.setId(cinema.getId());
        cinemaResponse.setName(cinema.getName());
        cinemaResponse.setReleaseDate(cinema.getReleaseDate());
        cinemaResponse.setDirector(cinema.getDirector());
        cinemaResponse.setPosterName(cinema.getPosterName());
        cinemaResponse.setStatus(cinema.getStatus());
        cinemaResponse.setCinemaType(cinema.getCinemaType().getName());
        cinemaResponse.setProducerName(cinema.getProducer().getName());
        cinemaResponse.setCreateTime(cinema.getCreateTime());
        cinemaResponse.setUpdateTime(cinema.getUpdateTime());
        return cinemaResponse;
    }
}
