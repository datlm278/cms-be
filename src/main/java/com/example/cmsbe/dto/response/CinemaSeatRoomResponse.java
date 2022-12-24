package com.example.cmsbe.dto.response;

import lombok.Data;

@Data
public class CinemaSeatRoomResponse {

    private CinemaRoomResponse cinemaRoom;
    private SeatRoomResponse seatRoom;

}
