package com.example.cmsbe.dto.request;

import lombok.Data;

@Data
public class CinemaRoomRequest {
    private Long id;
    private String roomCode;
    private Long totalSeats;
    private String screenType;
    private Long status;
}
