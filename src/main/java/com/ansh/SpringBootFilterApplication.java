package com.ansh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootFilterApplication {

	public static void main(String[] args) {
		log.info("App is starting");
		SpringApplication.run(SpringBootFilterApplication.class, args);
	}

}
