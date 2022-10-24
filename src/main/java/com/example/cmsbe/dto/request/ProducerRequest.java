package com.example.cmsbe.dto.request;

import lombok.Data;

@Data
public class ProducerRequest {
    private Long id;
    private String name;
    private String description;
    private Long status;
    private String image;
}
