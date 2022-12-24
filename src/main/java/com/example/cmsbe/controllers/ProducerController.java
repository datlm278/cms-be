package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ProducerRequest;
import com.example.cmsbe.dto.response.ProducerResponse;
import com.example.cmsbe.services.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(CMSConstant.PREFIX_API_URL + "/producer")
public class ProducerController {

    private final ProducerService producerService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProducerResponse> createProducer(@RequestPart("producer") ProducerRequest producerRequest,
                                                           @RequestPart("image")MultipartFile file) {
        LOGGER.info("[POST]{} create a new producer", CMSConstant.PREFIX_API_URL + "/producer/create");
        return ResponseEntity.ok().body(producerService.createProducer(producerRequest, file));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerResponse> updateProducer(@RequestPart("producer") ProducerRequest producerRequest,
                                                           @RequestParam Long id,
                                                           @RequestPart("image")MultipartFile file) {
        LOGGER.info("[POST]{} update a existed producer", CMSConstant.PREFIX_API_URL + "/producer/update?id=" + id);
        return ResponseEntity.ok().body(producerService.updateProducer(producerRequest, id, file));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProducer(@RequestParam Long id) {
        producerService.deleteProducer(id);
        LOGGER.info("[POST]{} delete a existed producer", CMSConstant.PREFIX_API_URL + "/producer/delete?id=" + id);
        return ResponseEntity.ok().body("Delete producer id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProducerResponse>> findAllProducer() {
        LOGGER.info("[GET]{} get list existed producer", CMSConstant.PREFIX_API_URL + "/producer/find-all");
        return ResponseEntity.ok().body(producerService.findAllProducer());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerResponse> findProducerById(@RequestParam Long id) {
        LOGGER.info("[GET]{} get a existed producer", CMSConstant.PREFIX_API_URL + "/producer/find-by-id?id=" + id);
        return ResponseEntity.ok().body(producerService.findProducerById(id));
    }
}
