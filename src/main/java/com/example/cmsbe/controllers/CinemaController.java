package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.example.cmsbe.services.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CMSConstant.PREFIX_API_URL + "/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> createCinema(@RequestBody CinemaRequest cinemaRequest) {
        return ResponseEntity.ok().body(cinemaService.createCinema(cinemaRequest));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> updateCinema(@RequestBody CinemaRequest cinemaRequest, @RequestParam Long id) {
        return ResponseEntity.ok().body(cinemaService.updateCinema(cinemaRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCinema(@RequestParam Long id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.ok().body("Delete cinema has id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaResponse>> findAllCinema() {
        return ResponseEntity.ok().body(cinemaService.findAllCinema());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> findCinemaById(@RequestParam Long id) {
        return ResponseEntity.ok().body(cinemaService.findCinemaById(id));
    }
}
