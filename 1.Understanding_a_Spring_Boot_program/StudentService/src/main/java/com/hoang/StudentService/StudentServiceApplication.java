package com.hoang.StudentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Enable Spring Boot’s auto-configuration mechanism configure your Spring application
@SpringBootApplication
public class StudentServiceApplication {

	public static void main(String[] args) {
		// Bootstrap and launch a Spring application from a Java main method
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}
