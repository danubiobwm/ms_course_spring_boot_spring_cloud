package com.dev.hruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaClient
@SpringBootApplication
public class HrUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrUserApplication.class, args);
	}

}
