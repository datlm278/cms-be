package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.example.cmsbe.services.cinema.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = CMSConstant.PREFIX_API_URL + "/cinema")
public class CinemaController {

    private final CinemaService cinemaService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CinemaResponse> create (@RequestPart("images") MultipartFile[] files,
                                                  @RequestPart("cinema") CinemaRequest cinema) throws IOException {
        LOGGER.info("[POST]{} create a new cinema", CMSConstant.PREFIX_API_URL + "/cinema/create");
        return ResponseEntity.ok().body(cinemaService.createCinema(files, cinema));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> updateCinema(@RequestPart("images") MultipartFile[] files,
                                                       @RequestPart("cinema") CinemaRequest cinemaRequest,
                                                       @RequestParam Long id) throws IOException {

        LOGGER.info("[POST]{} update a existed cinema", CMSConstant.PREFIX_API_URL + "/cinema/update?id=" + id);
        return ResponseEntity.ok().body(cinemaService.updateCinema(files, cinemaRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCinema(@RequestParam Long id) {
        cinemaService.deleteCinema(id);
        LOGGER.info("[POST]{} delete a existed cinema", CMSConstant.PREFIX_API_URL + "/cinema/delete?id=" + id);
        return ResponseEntity.ok().body("Delete cinema has id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaResponse>> findAllCinema() {
        LOGGER.info("[GET]{} get list existed cinema", CMSConstant.PREFIX_API_URL + "/cinema/find-all");
        return ResponseEntity.ok().body(cinemaService.findAllCinema());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaResponse> findCinemaById(@RequestParam Long id) {
        LOGGER.info("[GET]{} get a existed cinema", CMSConstant.PREFIX_API_URL + "/cinema/find-by-id?id=" + id);
        return ResponseEntity.ok().body(cinemaService.findCinemaById(id));
    }
}
