package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaTypeRequest;
import com.example.cmsbe.dto.response.CinemaTypeResponse;
import com.example.cmsbe.services.cinemaType.CinemaTypeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(CMSConstant.PREFIX_API_URL + "/cinema-type")
public class CinemaTypeController {

    private final CinemaTypeService cinemaTypeService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CinemaTypeController(CinemaTypeService cinemaTypeService) {
        this.cinemaTypeService = cinemaTypeService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> createCinemaType (@RequestBody CinemaTypeRequest cinemaTypeRequest) {
        LOGGER.info("[POST]{} create a new cinemaType", CMSConstant.PREFIX_API_URL + "/cinema-type/create");
        return ResponseEntity.ok().body(cinemaTypeService.createCinemaType(cinemaTypeRequest));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> updateCinemaType (@RequestBody CinemaTypeRequest cinemaTypeRequest, @RequestParam Long id) {
        LOGGER.info("[POST]{} update a existed cinemaTyoe", CMSConstant.PREFIX_API_URL + "/cinema-type/update?id=" + id);
        return ResponseEntity.ok().body(cinemaTypeService.updateCinemaType(cinemaTypeRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCinemaType (@RequestParam Long id) {
        cinemaTypeService.deleteCinemaType(id);
        LOGGER.info("[POST]{} delete a existed cinema-type", CMSConstant.PREFIX_API_URL + "/cinema-type/delete?id=" + id);
        return ResponseEntity.ok().body("Deleted cinema type has id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaTypeResponse>> findAllCinemaType() {
        LOGGER.info("[GET]{} get list existed cinemaType", CMSConstant.PREFIX_API_URL + "/cinema-type/find-all");
        return ResponseEntity.ok().body(cinemaTypeService.findAllCinemaType());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> findCinemaTypeById(@RequestParam Long id) {
        LOGGER.info("[GET]{} get a existed cinemaType", CMSConstant.PREFIX_API_URL + "/cinema-type/find-by-id?id=" + id);
        return ResponseEntity.ok().body(cinemaTypeService.findCinemaTypeById(id));
    }
}
