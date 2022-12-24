package com.example.cmsbe.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CinemaSeatRoomRequest {

    private String roomCode;
    private String screenType;
    private Long totalSeats;
    private Timestamp createTime;
    private Long status;
    private String rowName;
    private Long seatNumber;

}
