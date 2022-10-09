package com.hoang.Service2A;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2AController {

    // Respond any requests sent to http://localhost:8082/home
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "This is the Service2A home page.";
    }

}
