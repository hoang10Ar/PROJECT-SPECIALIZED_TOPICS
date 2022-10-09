package com.hoang.Server2Service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server2Controller {
    // Respond any requests sent to http://localhost:8082/ser2/home
    @RequestMapping(value = "/ser2/home", method = RequestMethod.GET)
    public String home() {
        return "Server 2 home";
    }

}
