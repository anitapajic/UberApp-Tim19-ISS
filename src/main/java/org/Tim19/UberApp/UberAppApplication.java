package org.Tim19.UberApp;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class UberAppApplication {
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(UberAppApplication.class, args);
	}

}
