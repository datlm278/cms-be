package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class CinemaShowtimeResponse {
    private Long id;
    private Date day;
    private Time hour;
    private String cinemaName;
    private Date releaseDate;
    private Date endingDate;
}
