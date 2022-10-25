package com.example.cmsbe.services.showtime;

import com.example.cmsbe.dto.request.ShowtimeRequest;
import com.example.cmsbe.dto.response.ShowtimeResponse;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface IShowtimeService {
    ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest);

    ShowtimeResponse updateShowtime(ShowtimeRequest showtimeRequest, Long id);

    void deleteShowtime(Long id);

    ShowtimeResponse findShowtimeById(Long id);

    ShowtimeResponse findShowtimeByDatetime(Date date, Time time);

    List<ShowtimeResponse> findShowtimeByDate(Date date);

    List<ShowtimeResponse> findShowtimeByTime(Time time);

    List<ShowtimeResponse> findAllShowtime();
}
