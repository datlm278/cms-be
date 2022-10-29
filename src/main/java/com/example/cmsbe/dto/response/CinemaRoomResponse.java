package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CinemaRoomResponse {
    private Long id;
    private String roomCode;
    private Long totalSeats;
    private String screenType;
    private Long status;
    private Timestamp createTime;
    private Timestamp updateTime;
}
