package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SeatRoomResponse {
    private Long id;
    private String rowName;
    private Long seatNumber;
    private String seatPosition;
    private Long status;
    private String roomCode;
    private Timestamp createTime;
    private Timestamp updateTime;
}
