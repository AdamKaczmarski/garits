package com.garits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GaritsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaritsApplication.class, args);
	}
}
