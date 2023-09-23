package com.upload.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringUploadPaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUploadPaiApplication.class, args);
	}

}
