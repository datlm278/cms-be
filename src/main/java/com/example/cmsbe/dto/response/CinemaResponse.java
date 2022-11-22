package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
public class CinemaResponse {
    private Long id;
    private String name;
    private Date releaseDate;
    private String director;
    private Long status;
    private Long cinemaTypeId;
    private String cinemaType;
    private Long producerId;
    private String producerName;
    private Timestamp createTime;
    private Timestamp updateTime;
}
