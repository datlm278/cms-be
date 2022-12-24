package com.example.cmsbe.services.cinemaRoom;

import com.example.cmsbe.dto.request.CinemaRoomRequest;
import com.example.cmsbe.dto.request.CinemaSeatRoomRequest;
import com.example.cmsbe.dto.response.CinemaRoomResponse;
import com.example.cmsbe.dto.response.CinemaSeatRoomResponse;

import java.util.List;

public interface ICinemaRoomService {

    void createCinemaRoomAndGenerateSeatRoom(CinemaRoomRequest request);

    void updateCinemaRoom(CinemaRoomRequest request, Long id);

    void deleteCinemaRoom(Long id);

    CinemaSeatRoomResponse createCinemaRoomAndSeatRoom(CinemaSeatRoomRequest request);

    List<CinemaRoomResponse> findAllCinemaRoom();

    CinemaRoomResponse findCinemaRoomById(Long id);
}
