package com.example.cmsbe.services.cinemaType;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaTypeRequest;
import com.example.cmsbe.dto.response.CinemaTypeResponse;
import com.example.cmsbe.entity.CinemaType;
import com.example.cmsbe.repositories.CinemaTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaTypeService implements ICinemaTypeService{

    @Autowired
    private final CinemaTypeRepository cinemaTypeRepository;

    public CinemaTypeService(CinemaTypeRepository cinemaTypeRepository) {
        this.cinemaTypeRepository = cinemaTypeRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CinemaTypeResponse createCinemaType(CinemaTypeRequest cinemaTypeRequest) {
        CinemaType type = modelMapper.map(cinemaTypeRequest, CinemaType.class);
        type.setCreateTime(Timestamp.from(Instant.now()));
        type = cinemaTypeRepository.save(type);
        return modelMapper.map(type, CinemaTypeResponse.class);
    }

    @Override
    public CinemaTypeResponse updateCinemaType(CinemaTypeRequest cinemaTypeRequest, Long id) {
        cinemaTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        if (!cinemaTypeRequest.getId().equals(id)) {
            throw new RuntimeException("cinema type request id not equal id request");
        }
        CinemaType type = modelMapper.map(cinemaTypeRequest, CinemaType.class);
        type.setUpdateTime(Timestamp.from(Instant.now()));
        type = cinemaTypeRepository.save(type);
        return modelMapper.map(type, CinemaTypeResponse.class);
    }

    @Override
    public void deleteCinemaType(Long id) {
        CinemaType type = cinemaTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        if (type.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("cinema type was deleted");
        }
        type.setStatus(CMSConstant.DELETE_STATUS);
        type.setUpdateTime(Timestamp.from(Instant.now()));
        cinemaTypeRepository.save(type);
    }

    @Override
    public CinemaTypeResponse findCinemaTypeById(Long id) {
        CinemaType type = cinemaTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema type isn't existed"));
        if (type.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("cinema type was deleted");
        }
        return modelMapper.map(type, CinemaTypeResponse.class);
    }

    @Override
    public List<CinemaTypeResponse> findAllCinemaType() {
        List<CinemaTypeResponse> cinemaTypeResponses = new ArrayList<>();
        List<CinemaTypeResponse> types = cinemaTypeRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x, CinemaTypeResponse.class))
                .collect(Collectors.toList());
        for (CinemaTypeResponse type : types) {
            if (!type.getStatus().equals(CMSConstant.DELETE_STATUS)) {
                cinemaTypeResponses.add(type);
            }
        }
        return cinemaTypeResponses;
    }
}
