package com.example.cmsbe.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProducerResponse {
    private Long id;
    private String name;
    private String description;
    private Long status;
    private String image;
    private Timestamp createTime;
    private Timestamp updateTime;
}
