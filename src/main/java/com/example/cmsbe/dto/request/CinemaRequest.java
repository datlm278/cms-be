package com.example.cmsbe.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class CinemaRequest {
    private Long id;
    private String name;
    private Date releaseDate;
    private String director;
    private String poster;
    private Long status;
    private Long cinemaTypeId;
    private Long producerId;
}
