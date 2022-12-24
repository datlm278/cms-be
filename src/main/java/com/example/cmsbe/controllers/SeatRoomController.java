package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.response.SeatRoomResponse;
import com.example.cmsbe.services.seatRoom.SeatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CMSConstant.PREFIX_API_URL + "/seat-room")
@Slf4j
public class SeatRoomController {

    private final SeatRoomService seatRoomService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SeatRoomController(SeatRoomService seatRoomService) {
        this.seatRoomService = seatRoomService;
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SeatRoomResponse>> findAllSeatRoom() {
        LOGGER.info("[GET]{} get list seat-rooms existed", CMSConstant.PREFIX_API_URL + "/seat-room/find-all");
        return ResponseEntity.ok().body(seatRoomService.findAllSeatRoom());
    }
}
