package com.example.cmsbe.mapper;

import com.example.cmsbe.dto.request.CinemaSeatRoomRequest;
import com.example.cmsbe.dto.response.SeatRoomResponse;
import com.example.cmsbe.entity.SeatRoom;

public class SeatRoomMapper {

    public static SeatRoom toEntity(CinemaSeatRoomRequest seatRoom) {
        SeatRoom entity = new SeatRoom();
        entity.setRowName(seatRoom.getRowName());
        entity.setSeatNumber(seatRoom.getSeatNumber());
        entity.setCreateTime(seatRoom.getCreateTime());
        entity.setStatus(seatRoom.getStatus());
        return entity;
    }

    public static SeatRoomResponse toDto(SeatRoom seatRoom) {
        SeatRoomResponse dto = new SeatRoomResponse();
        dto.setId(seatRoom.getId());
        dto.setRowName(seatRoom.getRowName());
        dto.setSeatNumber(seatRoom.getSeatNumber());
        dto.setSeatPosition(seatRoom.getSeatPosition());
        dto.setRoomCode(seatRoom.getCinemaRoom().getRoomCode());
        dto.setStatus(seatRoom.getStatus());
        dto.setCreateTime(seatRoom.getCreateTime());
        dto.setUpdateTime(seatRoom.getUpdateTime());
        return dto;
    }
}
