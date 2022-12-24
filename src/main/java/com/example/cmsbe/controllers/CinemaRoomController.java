package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaRoomRequest;
import com.example.cmsbe.dto.request.CinemaSeatRoomRequest;
import com.example.cmsbe.dto.response.CinemaRoomResponse;
import com.example.cmsbe.dto.response.CinemaSeatRoomResponse;
import com.example.cmsbe.services.cinemaRoom.CinemaRoomService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(CMSConstant.PREFIX_API_URL + "/cinema-room")
public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CinemaRoomController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCinemaRoom(@RequestBody CinemaRoomRequest cinemaRoomRequest) {
        cinemaRoomService.createCinemaRoomAndGenerateSeatRoom(cinemaRoomRequest);
        return ResponseEntity.ok().body("created cinema room successfully!");
    }

    @PostMapping(value = "/create-room-and-seat-room")
    public ResponseEntity<CinemaSeatRoomResponse> createCinemaRoomAndSeatRoom(@RequestBody CinemaSeatRoomRequest request) {
        LOGGER.info("[POST]{} create a new cinema-room and seat-room of new room", CMSConstant.PREFIX_API_URL + "cinema-room/create-room-and-seat-room");
        return ResponseEntity.ok().body(cinemaRoomService.createCinemaRoomAndSeatRoom(request));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCinemaRoom(@RequestBody CinemaRoomRequest cinemaRoomRequest, @RequestParam Long id) {
        cinemaRoomService.updateCinemaRoom(cinemaRoomRequest, id);
        return ResponseEntity.ok().body("Updated cinema room successfully!");
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCinemaRoom(@RequestParam Long id) {
        cinemaRoomService.deleteCinemaRoom(id);
        return ResponseEntity.ok().body("Deleted cinema room successfully!");
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<CinemaRoomResponse> findCinemaRoomById(Long id) {
        return ResponseEntity.ok().body(cinemaRoomService.findCinemaRoomById(id));
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaRoomResponse>> findAllCinemaRoom() {
        return ResponseEntity.ok().body(cinemaRoomService.findAllCinemaRoom());
    }
}
