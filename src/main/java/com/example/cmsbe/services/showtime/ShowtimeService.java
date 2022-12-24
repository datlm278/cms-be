package com.example.cmsbe.services.showtime;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ShowtimeRequest;
import com.example.cmsbe.dto.response.ShowtimeResponse;
import com.example.cmsbe.entity.Showtime;
import com.example.cmsbe.repositories.ShowtimeRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService implements IShowtimeService {

    @Autowired
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest) {
        Showtime showtime = modelMapper.map(showtimeRequest, Showtime.class);
        showtime.setCreateTime(Timestamp.from(Instant.now()));
        return modelMapper.map(showtimeRepository.save(showtime), ShowtimeResponse.class);
    }

    @Override
    public ShowtimeResponse updateShowtime(ShowtimeRequest showtimeRequest, Long id) {
        ShowtimeResponse response;
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new NotFoundException("showtime isn't existed"));
        if (!showtimeRequest.getId().equals(id)) {
            throw new RuntimeException("showtime request id is not equal id request");
        }
        if (showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("showtime was deleted");
        }
        try {
            showtime = modelMapper.map(showtimeRequest, Showtime.class);
            showtime.setUpdateTime(Timestamp.from(Instant.now()));
            showtime = showtimeRepository.save(showtime);
            response = modelMapper.map(showtime, ShowtimeResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    @Override
    public void deleteShowtime(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new NotFoundException("showtime isn't existed"));
        if (showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new NotFoundException("showtime was deleted");
        }
        showtime.setStatus(CMSConstant.DELETE_STATUS);
        showtimeRepository.save(showtime);
    }

    @Override
    public ShowtimeResponse findShowtimeById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new NotFoundException("showtime isn't existed"));
        if (showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
            throw new RuntimeException("showtime was deleted");
        }
        return modelMapper.map(showtime, ShowtimeResponse.class);
    }

    @Override
    public ShowtimeResponse findShowtimeByDatetime(String date, String time) {
        Showtime showtime = showtimeRepository.findShowtimeByDatetime(date, time);
        if (ObjectUtils.isEmpty(showtime)) {
            throw new NotFoundException("showtime isn't existed");
        } else {
            if (showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
                throw new RuntimeException("showtime was delete");
            }
        }
        return modelMapper.map(showtime, ShowtimeResponse.class);
    }

    @Override
    public List<ShowtimeResponse> findShowtimeByDate(String date) {
        List<Showtime> showtimes = showtimeRepository.findShowtimeByDate(date);
        return showtimes.stream().map(x -> modelMapper.map(x, ShowtimeResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowtimeResponse> findShowtimeByTime(String time) {
        List<Showtime> showtimes = showtimeRepository.findShowtimeByTime(time);
        return showtimes.stream().map(x -> modelMapper.map(x, ShowtimeResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowtimeResponse> findAllShowtime() {
        List<ShowtimeResponse> responses = new ArrayList<>();
        List<ShowtimeResponse> showtimes = showtimeRepository.findAll().stream().map(x -> modelMapper.map(x, ShowtimeResponse.class)).collect(Collectors.toList());
        for (ShowtimeResponse showtime : showtimes) {
            if (!showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
                responses.add(showtime);
            }
        }
        return responses;
    }
}
