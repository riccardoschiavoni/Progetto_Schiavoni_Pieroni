package com.project.progOgg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.Download;

@SpringBootApplication
public class ProgOggApplication {

	public static void main(String[] args) {
		new Download();
		SpringApplication.run(ProgOggApplication.class, args);
	}

}
