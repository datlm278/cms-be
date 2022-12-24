package com.example.cmsbe.controllers;

import com.example.cmsbe.common.constant.CMSConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(CMSConstant.PREFIX_API_URL)
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/hello-world")
    public ResponseEntity<String> hello(String name) {
        LOGGER.info("[GET]{} hello world controller", CMSConstant.PREFIX_API_URL + "/hello-world");
        return ResponseEntity.ok().body(String.format("Hello World, %s", name));
    }
}
