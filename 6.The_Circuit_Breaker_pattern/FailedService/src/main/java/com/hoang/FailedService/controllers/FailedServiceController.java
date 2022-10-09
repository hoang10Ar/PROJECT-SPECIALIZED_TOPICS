package com.hoang.FailedService.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailedServiceController {

    // Respond any requests sent to http://localhost:8081/data
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String getData() {
        return "Get data successfully";
    }

}
