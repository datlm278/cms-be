package com.example.cmsbe.dto.request;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ShowtimeRequest {
    private Long id;
    private Date day;
    private Time hour;
    private Long status;
}
