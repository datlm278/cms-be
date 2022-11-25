package com.example.cmsbe.services.producer;

import com.example.cmsbe.dto.request.ProducerRequest;
import com.example.cmsbe.dto.response.ProducerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProducerService {

    ProducerResponse createProducer(ProducerRequest producerRequest, MultipartFile file);

    ProducerResponse updateProducer(ProducerRequest producerRequest, Long id, MultipartFile file);

    void deleteProducer(Long id);

    List<ProducerResponse> findAllProducer();

    ProducerResponse findProducerById(Long id);
}
