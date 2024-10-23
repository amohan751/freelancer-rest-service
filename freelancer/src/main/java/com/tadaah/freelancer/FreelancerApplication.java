package com.tadaah.freelancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FreelancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelancerApplication.class, args);
	}

}
