package com.example.cmsbe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "${swagger.config.title}",
		description = "${swagger.config.title}",
		version = "${swagger.config.version}"),
		servers = {@Server(url = "${swagger.config.url}")})
public class CmsBeApplication {

	private static final Logger L = LoggerFactory.getLogger(CmsBeApplication.class);
	public static void main(String[] args) {

		System.setProperty("spring.devtools.restart.enabled", "true");
		System.setProperty("spring.profiles.default", "local");
		System.setProperty("java.net.preferIPv4Stack", "true");
		//1. Run spring application
		SpringApplication.run(CmsBeApplication.class, args);

		L.info("-----------------------------------------------------------");
		L.info("            Welcome to New CMS Service.");
		L.info("           Application start successfully.");
		L.info("-----------------------------------------------------------");
	}

}
