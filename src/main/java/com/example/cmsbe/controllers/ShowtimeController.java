package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ShowtimeRequest;
import com.example.cmsbe.dto.response.ShowtimeResponse;
import com.example.cmsbe.services.showtime.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(value = CMSConstant.PREFIX_API_URL + "/showtime")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowtimeResponse> createShowtime(@RequestBody ShowtimeRequest showtimeRequest) {
        return ResponseEntity.ok().body(showtimeService.createShowtime(showtimeRequest));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowtimeResponse> updateShowtime(@RequestBody ShowtimeRequest showtimeRequest, @RequestParam Long id) {
        return ResponseEntity.ok().body(showtimeService.updateShowtime(showtimeRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteShowtime(@RequestParam Long id) {
        showtimeService.deleteShowtime(id);
        return ResponseEntity.ok().body("Deleted showtime has id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShowtimeResponse>> findAllShowtime() {
        return ResponseEntity.ok().body(showtimeService.findAllShowtime());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowtimeResponse> findShowtimeById(@RequestParam Long id) {
        return ResponseEntity.ok().body(showtimeService.findShowtimeById(id));
    }

    @GetMapping(value = "/find-by-datetime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowtimeResponse> findShowtimeByDatetime(@RequestParam String date, @RequestParam String time) {
        return ResponseEntity.ok().body(showtimeService.findShowtimeByDatetime(date, time));
    }
}
