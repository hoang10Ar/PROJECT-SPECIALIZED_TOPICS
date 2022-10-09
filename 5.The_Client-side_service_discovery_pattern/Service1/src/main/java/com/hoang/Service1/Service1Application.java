package com.hoang.Service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

	@Bean	// Indicate that this is the method used for Spring to create an object
	@LoadBalanced	// Use load balancing mechanism during sending HTTP requests
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
