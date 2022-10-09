package com.hoang.Student1Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Student1Controller {
    // Getting the value of the propertyFileName property
    @Value("${propertyFileName}")
    private String name;

    // Responding the name attribute to requests sent to
    // http://localhost:8081/fileName
    @RequestMapping("/fileName")
    public String getPropertyValue() {
        return this.name;
    }
}
