package com.example.cmsbe.services.producer;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ProducerRequest;
import com.example.cmsbe.dto.response.ProducerResponse;
import com.example.cmsbe.entity.Producer;
import com.example.cmsbe.repositories.ProducerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProducerService implements IProducerService{

    @Autowired
    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProducerResponse createProducer(ProducerRequest producerRequest) {
        Producer producer = modelMapper.map(producerRequest, Producer.class);
        producer.setCreateTime(Timestamp.from(Instant.now()));
        return modelMapper.map(producerRepository.save(producer), ProducerResponse.class);
    }

    @Override
    public ProducerResponse updateProducer(ProducerRequest producerRequest, Long id) {
        producerRepository.findById(id).orElseThrow(() -> new NotFoundException("producer isn't existed"));
        if (!producerRequest.getId().equals(id)) {
            throw new RuntimeException("producer request id not equal id request");
        }
        Producer producer = modelMapper.map(producerRequest, Producer.class);
        producer.setUpdateTime(Timestamp.from(Instant.now()));
        return modelMapper.map(producerRepository.save(producer), ProducerResponse.class);
    }

    @Override
    public void deleteProducer(Long id) {
        Producer producer = producerRepository.findById(id).orElseThrow(() -> new NotFoundException("producer isn't existed"));
        if (producer.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("producer was deleted");
        }
        producer.setStatus(CMSConstant.DELETE_STATUS);
        producer.setUpdateTime(Timestamp.from(Instant.now()));
        producerRepository.save(producer);
    }

    @Override
    public List<ProducerResponse> findAllProducer() {
        List<ProducerResponse> producerResponses = new ArrayList<>();
        List<ProducerResponse> producers = producerRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, ProducerResponse.class))
                .collect(Collectors.toList());
        for (ProducerResponse producer : producers) {
            if (!Objects.equals(producer.getStatus(), CMSConstant.DELETE_STATUS)) {
                producerResponses.add(producer);
            }
        }
        return producerResponses;
    }

    @Override
    public ProducerResponse findProducerById(Long id) {
        Producer producer = producerRepository.findById(id).orElseThrow(() -> new NotFoundException("producer isn't existed"));
        if (Objects.equals(producer.getStatus(), CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("producer was deleted");
        }
        return modelMapper.map(producer, ProducerResponse.class);
    }
}
