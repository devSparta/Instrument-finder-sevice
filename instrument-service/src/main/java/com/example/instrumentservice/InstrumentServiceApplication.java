package com.example.instrumentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class InstrumentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InstrumentServiceApplication.class, args);
	}
}
