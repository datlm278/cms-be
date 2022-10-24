package com.example.cmsbe.services.producer;

import com.example.cmsbe.dto.request.ProducerRequest;
import com.example.cmsbe.dto.response.ProducerResponse;

import java.util.List;

public interface IProducerService {

    ProducerResponse createProducer(ProducerRequest producerRequest);

    ProducerResponse updateProducer(ProducerRequest producerRequest, Long id);

    void deleteProducer(Long id);

    List<ProducerResponse> findAllProducer();

    ProducerResponse findProducerById(Long id);
}
