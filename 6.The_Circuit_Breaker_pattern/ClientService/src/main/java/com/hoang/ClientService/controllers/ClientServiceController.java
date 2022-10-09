package com.hoang.ClientService.controllers;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientServiceController {

    // Respond any requests sent to http://localhost:8080
    @RequestMapping(value = "/", method = RequestMethod.GET)
    // Apply the circuit breaker pattern to this method
    // Observe requests sent from this method and calculate the failure rate
    @CircuitBreaker(name = "cirBreakerHome", fallbackMethod = "fallbackHome")
    public String callFailedService() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/data";

        // Send a request to the FailedService service
        String data = restTemplate.getForObject(url, String.class);
        System.out.println("Connect to the FailedService service successfully");

        return data;
    }

    // Get the duration of the Open state from the application.yml file
    @Value("${resilience4j.circuitbreaker.instances.cirBreakerHome.waitDurationInOpenState}")
    private String waitDurationInOpenState;

    // The fallback method for the "cirBreakerHome" circuit breaker
    // The fallback method will have the same return data type as the circuit breaker method
    // The return value of this fallback method will be respond back to the user
    // You can declare an extra method parameter of the class Exception to receive the error
    public String fallbackHome(Exception e) {
        // Send requests during the Open state will throw the CallNotPermittedException exception
        if(e instanceof CallNotPermittedException) {
            System.out.println("OPEN state. Please wait " + waitDurationInOpenState);
        } else {
            // If the FailedService service having problems, you will receive an exception
            System.out.println("Connect to the FailedService service failed");
        }

        return "Please try again later!";
    }

}
