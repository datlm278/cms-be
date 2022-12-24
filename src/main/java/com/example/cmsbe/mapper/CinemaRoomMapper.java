package com.example.cmsbe.mapper;

import com.example.cmsbe.dto.request.CinemaSeatRoomRequest;
import com.example.cmsbe.entity.CinemaRoom;

public class CinemaRoomMapper {

    public static CinemaRoom toEntity(CinemaSeatRoomRequest cinemaRoom) {
        CinemaRoom entity = new CinemaRoom();
        entity.setRoomCode(cinemaRoom.getRoomCode());
        entity.setScreenType(cinemaRoom.getScreenType());
        entity.setCreateTime(cinemaRoom.getCreateTime());
        entity.setTotalSeats(cinemaRoom.getTotalSeats());
        entity.setStatus(cinemaRoom.getStatus());
        return entity;
    }

}
