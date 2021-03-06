package com.example.ForestProtect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ForestProtectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForestProtectApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ForestProtectApplication.class);
	}
}
