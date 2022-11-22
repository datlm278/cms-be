package com.example.cmsbe.services.cinema;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.common.utils.UploadFileUtils;
import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.example.cmsbe.entity.Cinema;
import com.example.cmsbe.entity.CinemaType;
import com.example.cmsbe.entity.Image;
import com.example.cmsbe.entity.Producer;
import com.example.cmsbe.mapper.CinemaMapper;
import com.example.cmsbe.repositories.CinemaRepository;
import com.example.cmsbe.repositories.CinemaTypeRepository;
import com.example.cmsbe.repositories.ProducerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CinemaService implements ICinemaService {

    @Autowired
    private final CinemaRepository cinemaRepository;

    @Autowired
    private final CinemaTypeRepository cinemaTypeRepository;

    @Autowired
    private final ProducerRepository producerRepository;

    public CinemaService(CinemaRepository cinemaRepository, CinemaTypeRepository cinemaTypeRepository, ProducerRepository producerRepository) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaTypeRepository = cinemaTypeRepository;
        this.producerRepository = producerRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ServletContext context;


    @Override
    public CinemaResponse createCinema(MultipartFile[] files, CinemaRequest cinemaRequest) throws IOException {
        CinemaType type = cinemaTypeRepository
                .findById(cinemaRequest.getCinemaTypeId())
                .orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        Producer producer = producerRepository
                .findById(cinemaRequest.getProducerId())
                .orElseThrow(() -> new NotFoundException("producer isn't existed"));

        Cinema cinema = modelMapper.map(cinemaRequest, Cinema.class);
        Set<Image> images = UploadFileUtils.upload(files);
        cinema.setCinemaImage(images);
        cinema.setCreateTime(Timestamp.from(Instant.now()));
        cinema.setProducer(producer);
        cinema.setCinemaType(type);
        cinema = cinemaRepository.save(cinema);
        return CinemaMapper.toDto(cinema);
    }

    @Override
    public CinemaResponse updateCinema(MultipartFile file, String request, Long id) throws IOException {
        cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema isn't existed"));

        CinemaRequest cinemaRequest = new ObjectMapper().readValue(request, CinemaRequest.class);

        CinemaType type = cinemaTypeRepository
                .findById(cinemaRequest.getCinemaTypeId())
                .orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        Producer producer = producerRepository
                .findById(cinemaRequest.getProducerId())
                .orElseThrow(() -> new NotFoundException("producer isn't existed"));

        if (!cinemaRequest.getId().equals(id)) {
            throw new RuntimeException("cinema request id not equal id request");
        }

        Cinema cinema = modelMapper.map(cinemaRequest, Cinema.class);
        cinema.setUpdateTime(Timestamp.from(Instant.now()));
        cinema.setProducer(producer);
        cinema.setCinemaType(type);
        cinema = cinemaRepository.save(cinema);
        return CinemaMapper.toDto(cinema);
    }

    @Override
    public void deleteCinema(Long id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema isn't existed"));
        if (cinema.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("cinema was deleted");
        }
        cinema.setStatus(CMSConstant.DELETE_STATUS);
        cinema.setUpdateTime(Timestamp.from(Instant.now()));
        cinemaRepository.save(cinema);
    }

    @Override
    public List<CinemaResponse> findAllCinema() {
        List<CinemaResponse> cinemaResponses = new ArrayList<>();
        List<Cinema> cinemas = cinemaRepository.findAll();
        for (Cinema cinema : cinemas) {
            if (!cinema.getStatus().equals(CMSConstant.DELETE_STATUS)) {
                cinemaResponses.add(CinemaMapper.toDto(cinema));
            }
        }
        return cinemaResponses;
    }

    @Override
    public CinemaResponse findCinemaById(Long id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema isn't existed"));
        if (cinema.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("cinema was deleted");
        }
        return CinemaMapper.toDto(cinema);
    }
}
