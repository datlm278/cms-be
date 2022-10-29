package com.example.cmsbe.services.cinemaRoom;

import com.example.cmsbe.dto.request.CinemaRoomRequest;
import com.example.cmsbe.dto.response.CinemaRoomResponse;

import java.util.List;

public interface ICinemaRoomService {

    void createCinemaRoom(CinemaRoomRequest request);

    void updateCinemaRoom(CinemaRoomRequest request, Long id);

    void deleteCinemaRoom(Long id);

    List<CinemaRoomResponse> findAllCinemaRoom();

    CinemaRoomResponse findCinemaRoomById(Long id);
}
