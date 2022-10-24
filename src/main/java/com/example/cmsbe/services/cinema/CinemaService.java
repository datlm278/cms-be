package com.example.cmsbe.services.cinema;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaRequest;
import com.example.cmsbe.dto.response.CinemaResponse;
import com.example.cmsbe.entity.Cinema;
import com.example.cmsbe.entity.CinemaType;
import com.example.cmsbe.entity.Producer;
import com.example.cmsbe.repositories.CinemaRepository;
import com.example.cmsbe.repositories.CinemaTypeRepository;
import com.example.cmsbe.repositories.ProducerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public CinemaResponse createCinema(CinemaRequest cinemaRequest) {

        CinemaType type = cinemaTypeRepository
                .findById(cinemaRequest.getCinemaTypeId())
                .orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        Producer producer = producerRepository
                .findById(cinemaRequest.getProducerId())
                .orElseThrow(() -> new NotFoundException("producer isn't existed"));

        Cinema cinema = modelMapper.map(cinemaRequest, Cinema.class);
        cinema.setCreateTime(Timestamp.from(Instant.now()));
        cinema.setProducer(producer);
        cinema.setCinemaType(type);
        cinema = cinemaRepository.save(cinema);
        return cinemaBuilder(cinema);
    }

    @Override
    public CinemaResponse updateCinema(CinemaRequest cinemaRequest, Long id) {
        cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema isn't existed"));

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
        return cinemaBuilder(cinema);
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
                cinemaResponses.add(cinemaBuilder(cinema));
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
        return cinemaBuilder(cinema);
    }

    private CinemaResponse cinemaBuilder(Cinema cinema) {
        CinemaResponse cinemaResponse = new CinemaResponse();
        cinemaResponse.setId(cinema.getId());
        cinemaResponse.setName(cinema.getName());
        cinemaResponse.setReleaseDate(cinema.getReleaseDate());
        cinemaResponse.setEndingDate(cinema.getEndingDate());
        cinemaResponse.setDirector(cinema.getDirector());
        cinemaResponse.setPoster(cinema.getPoster());
        cinemaResponse.setStatus(cinema.getStatus());
        cinemaResponse.setCinemaType(cinema.getCinemaType().getName());
        cinemaResponse.setProducerName(cinema.getProducer().getName());
        cinemaResponse.setCreateTime(cinema.getCreateTime());
        cinemaResponse.setUpdateTime(cinema.getUpdateTime());
        return cinemaResponse;
    }
}
