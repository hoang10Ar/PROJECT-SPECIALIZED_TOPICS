package com.hoang.ClientService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {
    // Respond any requests sent to http://localhost:8083/1
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String home1() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/ser1/home";

        // Send a request to the Server1Service service
        return restTemplate.getForObject(url, String.class);
    }

    // Respond any requests sent to http://localhost:8083/2
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String home2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/ser2/home";

        // Send a request to the Server2Service service
        return restTemplate.getForObject(url, String.class);
    }

}
