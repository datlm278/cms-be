package com.example.cmsbe.dto.request;

import lombok.Data;

@Data
public class SeatRoomRequest {
    private Long id;
    private String rowName;
    private Long seatNumber;
    private String seatPosition;
    private Long status;
    private String roomCode;
}
