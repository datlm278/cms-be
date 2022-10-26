package com.example.cmsbe.common.config;

import com.example.cmsbe.common.constant.CMSConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${cms.cors.origins}")
    private String corsOrigins;
    @Value("${cms.methods.allowed}")
    private String allowedMethods;
    @Value("${cms.header.allowed}")
    private String allowedHeaders;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CMSConstant.PREFIX_API_URL + "/**")
                .allowCredentials(true)
                .allowedOrigins(corsOrigins.split(","))
                .allowedMethods(allowedMethods.split(","))
                .allowedHeaders(allowedHeaders.split(","))
                .maxAge(3600)
                .exposedHeaders("Content-Disposition");
    }
}
