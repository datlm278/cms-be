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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = CMSConstant.PREFIX_API_URL + "/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CinemaResponse> create (@RequestPart("poster") MultipartFile[] files,
                                                  @RequestPart("cinema") CinemaRequest cinema) throws IOException {
        return ResponseEntity.ok().body(cinemaService.createCinema(files, cinema));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> updateCinema(@RequestParam("poster") MultipartFile[] files, @RequestParam("cinema") String cinemaRequest, @RequestParam Long id) throws IOException {
        return ResponseEntity.ok().body(cinemaService.updateCinema(files, cinemaRequest, id));
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
