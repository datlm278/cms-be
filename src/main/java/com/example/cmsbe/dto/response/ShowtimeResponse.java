package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
public class ShowtimeResponse {
    private Long id;
    private Date day;
    private Time hour;
    private Long status;
    private Timestamp createTime;
    private Timestamp updateTime;
}
