package com.hoang.Service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Service1Controller {

    @Autowired   // Auto inject an object having this data type is created by Spring
    private RestTemplate restTemplate;

    // Respond any requests sent to http://localhost:8081/home
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        // Send a request to http://Service2/home and return the response
        return restTemplate.getForObject("http://Service2/home", String.class);
    }

}
