package com.project.progOgg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.Download;

import java.io.IOException;

@SpringBootApplication

public class ProgOggApplication {

	public static void main(String[] args) throws IOException {
	new Download();
	SpringApplication.run(ProgOggApplication.class, args);
	}

}
