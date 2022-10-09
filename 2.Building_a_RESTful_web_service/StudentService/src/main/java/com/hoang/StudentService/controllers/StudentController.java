package com.hoang.StudentService.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController     // Identify a class as a controller
public class StudentController {
    // Find and store the "my_greeting" property value into your variable
    @Value("${my_greeting}")
    private String greeting;

    // Ensure that HTTP GET requests to “/home/<any value>” are mapped
    // to the home() method
    @RequestMapping(value = "/home/{name}", method = RequestMethod.GET)
    public String home(@PathVariable("name") String name) {
        // "@PathVariable("name") String name": handle template variables
        // in the request URI mapping ("/{name}") and set them as method
        // parameters ("String name")

        // Respond the string back to the client
        return "<h1>" + greeting + " " + name + "</h1>";
    }
}
