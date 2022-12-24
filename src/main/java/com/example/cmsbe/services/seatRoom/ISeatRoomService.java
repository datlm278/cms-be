package com.example.cmsbe.services.seatRoom;

import com.example.cmsbe.dto.request.SeatRoomRequest;
import com.example.cmsbe.dto.response.SeatRoomResponse;

import java.util.List;

public interface ISeatRoomService {

    SeatRoomResponse create(SeatRoomRequest seatRoomRequest);

    SeatRoomResponse update(SeatRoomRequest seatRoomRequest, Long id);

    void delete(Long id);

    List<SeatRoomResponse> findAllSeatRoom();

    SeatRoomResponse findSeatRoomById(Long id);

}
