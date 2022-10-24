package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaTypeRequest;
import com.example.cmsbe.dto.response.CinemaTypeResponse;
import com.example.cmsbe.services.cinemaType.CinemaTypeService;
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

@RestController
@RequestMapping(CMSConstant.PREFIX_API_URL + "/cinema-type")
public class CinemaTypeController {

    @Autowired
    private CinemaTypeService cinemaTypeService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> createCinemaType (@RequestBody CinemaTypeRequest cinemaTypeRequest) {
        return ResponseEntity.ok().body(cinemaTypeService.createCinemaType(cinemaTypeRequest));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> updateCinemaType (@RequestBody CinemaTypeRequest cinemaTypeRequest, @RequestParam Long id) {
        return ResponseEntity.ok().body(cinemaTypeService.updateCinemaType(cinemaTypeRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCinemaType (@RequestParam Long id) {
        cinemaTypeService.deleteCinemaType(id);
        return ResponseEntity.ok().body("Deleted cinema type has id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaTypeResponse>> findAllCinemaType() {
        return ResponseEntity.ok().body(cinemaTypeService.findAllCinemaType());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CinemaTypeResponse> findCinemaTypeById(@RequestParam Long id) {
        return ResponseEntity.ok().body(cinemaTypeService.findCinemaTypeById(id));
    }
}
