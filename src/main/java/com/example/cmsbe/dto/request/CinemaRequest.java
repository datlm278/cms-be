package com.example.cmsbe.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class CinemaRequest {
    private Long id;
    private String name;
    private Date releaseDate;
    private Date endingDate;
    private String director;
    private Long status;
    private String poster;
    private Long cinemaTypeId;
    private Long producerId;
}
