package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ProducerRequest;
import com.example.cmsbe.dto.response.ProducerResponse;
import com.example.cmsbe.services.producer.ProducerService;
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
@RequestMapping(CMSConstant.PREFIX_API_URL + "/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerResponse> createProducer(@RequestBody ProducerRequest producerRequest) {
        return ResponseEntity.ok().body(producerService.createProducer(producerRequest));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerResponse> updateProducer(@RequestBody ProducerRequest producerRequest, @RequestParam Long id) {
        return ResponseEntity.ok().body(producerService.updateProducer(producerRequest, id));
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProducer(@RequestParam Long id) {
        producerService.deleteProducer(id);
        return ResponseEntity.ok().body("Delete producer id= " + id + " successfully!");
    }

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProducerResponse>> findAllProducer() {
        return ResponseEntity.ok().body(producerService.findAllProducer());
    }

    @GetMapping(value = "/find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProducerResponse> findProducerById(@RequestParam Long id) {
        return ResponseEntity.ok().body(producerService.findProducerById(id));
    }
}
