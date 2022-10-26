package com.example.cmsbe.services.showtime;

import com.example.cmsbe.dto.request.ShowtimeRequest;
import com.example.cmsbe.dto.response.ShowtimeResponse;

import java.util.List;

public interface IShowtimeService {
    ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest);

    ShowtimeResponse updateShowtime(ShowtimeRequest showtimeRequest, Long id);

    void deleteShowtime(Long id);

    ShowtimeResponse findShowtimeById(Long id);

    ShowtimeResponse findShowtimeByDatetime(String date, String time);

    List<ShowtimeResponse> findShowtimeByDate(String date);

    List<ShowtimeResponse> findShowtimeByTime(String time);

    List<ShowtimeResponse> findAllShowtime();
}
