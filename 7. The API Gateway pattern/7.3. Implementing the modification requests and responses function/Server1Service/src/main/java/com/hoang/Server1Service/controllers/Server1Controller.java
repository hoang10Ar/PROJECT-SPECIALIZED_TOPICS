package com.hoang.Server1Service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server1Controller {
    // Respond any requests sent to http://localhost:8081/ser1/homeV2
    @RequestMapping(value = "/ser1/homeV2", method = RequestMethod.GET)
    public String home() {
        return "Server 1 home new version";
    }

}
