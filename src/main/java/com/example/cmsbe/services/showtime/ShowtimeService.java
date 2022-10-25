package com.example.cmsbe.services.showtime;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.ShowtimeRequest;
import com.example.cmsbe.dto.response.ShowtimeResponse;
import com.example.cmsbe.entity.Showtime;
import com.example.cmsbe.repositories.ShowtimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ShowtimeService implements IShowtimeService{

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
        showtimeRepository.findById(id).orElseThrow(() -> new NotFoundException("showtime isn't existed"));
        if (!showtimeRequest.getId().equals(id)) {
            throw new RuntimeException("showtime request id is not equal id request");
        }
        try {
            Showtime showtime = modelMapper.map(showtimeRequest, Showtime.class);
            if (showtime.getStatus().equals(CMSConstant.DELETE_STATUS)) {
                throw new NotFoundException("showtime was deleted");
            }
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
        return null;
    }

    @Override
    public ShowtimeResponse findShowtimeByDatetime(Date date, Time time) {
        return null;
    }

    @Override
    public List<ShowtimeResponse> findShowtimeByDate(Date date) {
        return null;
    }

    @Override
    public List<ShowtimeResponse> findShowtimeByTime(Time time) {
        return null;
    }

    @Override
    public List<ShowtimeResponse> findAllShowtime() {
        return null;
    }
}
